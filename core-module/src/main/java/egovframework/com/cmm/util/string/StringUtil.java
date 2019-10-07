package egovframework.com.cmm.util.string;

import java.lang.Character.UnicodeBlock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import egovframework.com.cmm.util.model.Jaso;
import egovframework.com.cmm.util.model.Syllable;

public class StringUtil {
	public static char[] ChoSung = { 0x3131, 0x3132, 0x3134, 0x3137, 0x3138,
		0x3139, 0x3141, 0x3142, 0x3143, 0x3145, 0x3146, 0x3147, 0x3148,
		0x3149, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e };
	public static char[] JungSung = { 0x314f, 0x3150, 0x3151, 0x3152, 0x3153,
		0x3154, 0x3155, 0x3156, 0x3157, 0x3158, 0x3159, 0x315a, 0x315b,
		0x315c, 0x315d, 0x315e, 0x315f, 0x3160, 0x3161, 0x3162, 0x3163 };
	public static char[] JongSung = { 0x0000, 0x3131, 0x3132, 0x3133, 0x3134,
		0x3135, 0x3136, 0x3137, 0x3139, 0x313a, 0x313b, 0x313c, 0x313d,
		0x313e, 0x313f, 0x3140, 0x3141, 0x3142, 0x3144, 0x3145, 0x3146,
		0x3147, 0x3148, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e };

	public static List<String> split(String source, String spliter){
		List<String> splitedList = new ArrayList<String>();
		StringBuffer sb = new StringBuffer(source);
		int prevIndex = -1;
		int index = 0;

		while(true){			
			index = sb.indexOf(spliter, prevIndex+1);			
			if(index == -1){
				splitedList.add(sb.substring(prevIndex+1));
				break;
			}
			splitedList.add(sb.substring(prevIndex+1,index));			
			prevIndex = index + spliter.length()-1;
		}
		sb = null;

		return splitedList;
	}

	public static List<String> ngram(String str,int n,String begin,String end){

		List<String> ngramList = new ArrayList<String>();
		for(int i=0;i<n-1;i++){
			if(begin != null){
				str = begin+str;
			}
			if(end != null){
				str = str + end;
			}			
		}
		for(int i=0;i<str.length();i++){
			if(i+n > str.length()){
				break;
			}			
			StringBuilder sb = new StringBuilder();
			for(int j=i;j<i+n;j++){
				sb.append(str.charAt(j));
			}
			ngramList.add(sb.toString());
			sb = null;
		}
		return ngramList;
	}

	public static List<Jaso> korean2JasoList(String str) {
		int length = str.length();		
		List<Jaso> jasoList = new ArrayList<Jaso>();

		for(int i=0;i<length;i++){

			//한글 str을 음절 정보를 담고 있는 Syllable 형태로 반환
			Syllable syllable =StringUtil.hangul2Syllable(str.charAt(i));

			//입력 str의 음절 정보가 부정확한 경우(ㅋ,ㅏ,a, b 등등) 자소 타입을 ETC로 정함
			if(syllable == null){
				jasoList.add(new Jaso(str.charAt(i), Jaso.TYPE.ETC, -1));
				continue;
			}

			//초성 정보를 Jaso로 변환
			Jaso chosung = new Jaso(syllable.getChosung(),Jaso.TYPE.CHOSUNG,syllable.getChosungIndex());
			jasoList.add(chosung);

			//중성 정보를 Jaso로 변환
			Jaso jungsung = new Jaso(syllable.getJungsung(),Jaso.TYPE.JUNGSUNG,syllable.getJungsungIndex());
			jasoList.add(jungsung);

			//종성이 있는 경우 종성 정보를 Jaso로 변환
			//			if(syllable.getJongsungIndex() != 0){
			Jaso jongsung = new Jaso(syllable.getJongsung(),Jaso.TYPE.JONGSUNG,syllable.getJongsungIndex());
			jasoList.add(jongsung);
			syllable =null;
			chosung = null;
			jungsung = null;
			jongsung = null;
			//			}
		}
		return jasoList;
	}

	public static String restoreJasoList2Korean(List<Jaso> jasoList){
		return restoreJasoList2Korean(0, jasoList.size()-1, jasoList);
	}

	public static String restoreJasoList2Korean(int begin, int end, List<Jaso> jasoList) {

		int cho=-1,jung=-1,jong=-1;

		StringBuffer koreanStr = new StringBuffer();
		for(int i=begin;i<=end;i++){

			Jaso jaso = jasoList.get(i);
			if(jaso.getType() == Jaso.TYPE.CHOSUNG){
				cho = jaso.getIndex();
			}
			else if(jaso.getType() == Jaso.TYPE.JUNGSUNG){
				jung = jaso.getIndex();
			}
			else if(jaso.getType() == Jaso.TYPE.JONGSUNG){
				jong = jaso.getIndex();
			}
			else{				
				jong = Arrays.binarySearch(StringUtil.JongSung, jaso.getJaso());
			}

			if(i+1 > end || jasoList.get(i+1).getType() == Jaso.TYPE.CHOSUNG || jasoList.get(i+1).getType() == Jaso.TYPE.ETC || jasoList.get(i+1).getType() == Jaso.TYPE.ETC){
				//종성이 있는 경우
				if(jong != -1){
					//초성이 있는 경우
					if(cho != -1){
						koreanStr.append((char)(0xAC00+cho*588+jung*28+jong));
					}
					//초성이 없는 경우
					else{						
						koreanStr.append(jaso.getJaso());
					}
				}else if(jung != -1){
					koreanStr.append((char)(0xAC00+cho*588+jung*28));
				}else{
					koreanStr.append(jaso.getJaso());
				}
				cho = jung = jong = -1;
			}
			jaso = null;
		}	

		return koreanStr.toString();
	}

	public static boolean isKorean(char ch){
		UnicodeBlock unicodeBlock = UnicodeBlock.of(ch);
		if(UnicodeBlock.HANGUL_SYLLABLES == unicodeBlock ||
				UnicodeBlock.HANGUL_COMPATIBILITY_JAMO == unicodeBlock ||
				UnicodeBlock.HANGUL_JAMO == unicodeBlock)
			return true;
		else return false;
	}
	
	public static String getKorean(String in){
		StringBuffer sb = new StringBuffer();
		char[] charArray = in.toCharArray();
		for (char ch : charArray) {
			UnicodeBlock unicodeBlock = UnicodeBlock.of(ch);
			if(UnicodeBlock.HANGUL_SYLLABLES == unicodeBlock ||
					UnicodeBlock.HANGUL_COMPATIBILITY_JAMO == unicodeBlock ||
					UnicodeBlock.HANGUL_JAMO == unicodeBlock){
				sb.append(ch);
			}
		}
		return sb.toString();
	}

	public static boolean isEnglish(char ch){
		UnicodeBlock unicodeBlock = UnicodeBlock.of(ch);
		if(unicodeBlock == UnicodeBlock.BASIC_LATIN){
			if (((ch >= 'A') && (ch <= 'Z')) || ((ch >= 'a') && (ch <= 'z'))) {
				return true;
			}
		}
		return false;		
	}

	public static boolean isJapanese(char ch){
		UnicodeBlock unicodeBlock = UnicodeBlock.of(ch);
		if(UnicodeBlock.KATAKANA.equals(unicodeBlock) 
				|| UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS.equals(unicodeBlock)
				|| UnicodeBlock.HIRAGANA.equals(unicodeBlock)){
			return true;
		}
		return false;
	}

	public static boolean isForeign(char ch){
		UnicodeBlock unicodeBlock = UnicodeBlock.of(ch);
		if(unicodeBlock == UnicodeBlock.HIRAGANA ||
				unicodeBlock == UnicodeBlock.KATAKANA ||
				unicodeBlock == UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS){
			return true;
		}
		return false;
	}

	public static boolean isChinese(char ch){
		UnicodeBlock unicodeBlock = UnicodeBlock.of(ch);
		if(UnicodeBlock.CJK_COMPATIBILITY.equals(unicodeBlock)				
				|| UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS.equals(unicodeBlock)
				|| UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A.equals(unicodeBlock)
				|| UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B.equals(unicodeBlock)
				|| UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS.equals(unicodeBlock)){
			return true;
		}
		return false;
	}

	public static UnicodeBlock getUnicodeBlock(char ch){
		return UnicodeBlock.of(ch);
	}

	public static boolean isNumeric(char ch){
		if(Character.isDigit(ch)){
			return true;
		}
		return false;
	}


	public static String korean2JasoString(String word){
		return korean2JasoString(word, false);
	}

	public static String korean2JasoString(String word,boolean fixJongsung){
		String key = "";
		for(int j=0;j<word.length();j++){
			Syllable s = StringUtil.hangul2Syllable(word.charAt(j));
			if(s == null){
				key += word.charAt(j);
				continue;
			}
			key += s.getChosung();
			key += s.getJungsung();
			if(fixJongsung){
				key += s.getJongsung();
			}else{
				if(s.getJongsung() != 'x'){
					key += s.getJongsung();
				}
			}
			s = null;
		}		
		return key;
	}

	public static Syllable hangul2Syllable(char ch){
		int cho,jung,jong,tmp;
		if(ch >= 0xAC00 && ch <=0xD7A3){
			tmp = ch - 0xAC00;
			cho = tmp / (21*28);
			tmp = tmp % (21*28);			
			jung = tmp / 28;
			jong = tmp % 28;			

			if(jong != 0){
				return new Syllable(ChoSung[cho], cho,JungSung[jung], jung,JongSung[jong],jong);
			}else{
				return new Syllable(ChoSung[cho], cho,JungSung[jung],jung, 'x',jong);
			}
		}		
		return null;
	}

	public static String restoreJasoword2Korean(String jasoWord){

		StringBuilder sb = new StringBuilder();
		int length = jasoWord.length();

		char cho, jung, jong;
		cho = jung = jong = ' ';

		for(int i=0; i<length; i++) {

			//자소가 자음인 경우
			if(jasoWord.charAt(i) >= 0x3131 && jasoWord.charAt(i) <= 0x314e) {

				//초성이 비어있으면 초성으로 셋팅
				if(cho == ' ') {
					cho = jasoWord.charAt(i);
				} else {
					//초성이 이미 있고 중성이 없으면 이미 있는 초성을 음절로 출력하고 현재 자음를 초성으로 셋팅
					if(jung == ' ') {
						sb.append(cho);
						cho = jasoWord.charAt(i);
					} else {
						//마지막 자소가 아니고
						//초성이 있고 중성도 있지만 종성이 없으면 다음 자소를 확인해 
						//자음이면 종성으로 모음이면 현재까지의 초성 중성을 음절로 출력하고 현재 자음을 초성으로 셋팅
						if(i+1 < length) {
							if(jasoWord.charAt(i+1) >= 0x3131 && jasoWord.charAt(i+1) <= 0x314e) {
								jong = jasoWord.charAt(i);
								sb.append(combineJaso2Emjeol(cho, jung, jong));
								cho = jung = jong = ' ';
							} else {
								sb.append(combineJaso2Emjeol(cho, jung, jong));
								cho = jasoWord.charAt(i);
								jung = jong = ' ';
							}
						} else {
							//마지막 자소이면 현재 자음을 종성으로 넣어 음절로 만듬
							jong = jasoWord.charAt(i);
							sb.append(combineJaso2Emjeol(cho, jung, jong));
							cho = jung = jong = ' ';
						}

					}
				}
			}
			//자소가 모음인 경우
			else {
				//초성이 비어있으면 자소만 바로 출력
				if(cho == ' ') {
					sb.append(jasoWord.charAt(i));
				} 
				//초성이 있으면
				else {
					jung = jasoWord.charAt(i);
				}
			}
		}

		if(cho != ' ' || jung != ' ' || jong != ' ') {
			sb.append(combineJaso2Emjeol(cho, jung, jong));
		}

		return sb.toString();
	}

	private static char combineJaso2Emjeol(char cho, char jung, char jong) {


		char emjeol = 0;

		if(cho != ' ') {
			if(jung != ' ') {
				int choIndex = Arrays.binarySearch(ChoSung, cho);
				int jungIndex = jung - 0x314f;

				emjeol = (char) (0xAC00 + (choIndex * 588) + (jungIndex * 28));

				if(jong != ' ') {
					int jongIndex = Arrays.binarySearch(JongSung, jong);
					emjeol += jongIndex;
				}
			} else {
				emjeol = cho;
			}
		} else {
			if(jong != ' ') {
				emjeol = jung;
			} else {
				emjeol = jong;
			}
		}

		return emjeol;

	}
}
