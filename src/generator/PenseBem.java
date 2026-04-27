package generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PenseBem {

	// Esse SAMPLE é baseado no modelo de 99 livros
	private static final String SAMPLE = "dbaadcbdaadcbbcbdddbdababdacaccbdababdacaccbdbbcdcdddacaabcaabadbbbcdcdddaccbadbadbbddccbadbadbbdcccbadbabbdabbdabdabccddccddaacdbbddbbcdcbbbdabdddcdcbaadcbdaadcbbcddddbdababdacaccbdababdacaccbddbcdcdddacaabcaabadbbbcdcdddacabadbadbbddccbadbadbbdcccbadbadbdabbdabdabccddccddaacdbbddbbddcbbbdabdddcdcbaadcbdaadcbbcddddbdababdacaccbdababdacaccbdddcdcdddacaabcaabadbbbcdcdddacaaadbadbbddccbadbadbbdcccbadbadbdabbdabdabccddccddaacdbbddbbdacbbbdabdddcdcbbadcbdaadcbbcddbdbdababdacaccbdababdacaccbdddbdcdddacaabcaabadbbbcdcdddacaabdbadbbddccbadbadbbdcccbadbadbbabbdabdabccddccddaacdbbddbbdabbbbdabdddcdcbbbdcbdaadcbbcddbabdababdacaccbdababdacaccbdddbdcdddacaabcaabadbbbcdcdddacaabcbadbbddccbadbadbbdcccbadbadbbdbbdabdabccddccddaacdbbddbbdabdbbdabdddcdcbbbacbdaadcbbcddbaadababdacaccbdababdacaccbdddbdadddacaabcaabadbbbcdcdddacaabcaadbbddccbadbadbbdcccbadbadbbdcbdabdabccddccddaacdbbddbbdabdabdabdddcdcbbbacbdaadcbbcddbaadababdacaccbdababdacaccbdddbdabddacaabcaabadbbbcdcdddacaabcaadbbddccbadbadbbdcccbadbadbbdcadabdabccddccddaacdbbddbbdabdabdabdddcdcbbbacadaadcbbcddbaadcbabdacaccbdababdacaccbdddbdabadacaabcaabadbbbcdcdddacaabcaabbbddccbadbadbbdcccbadbadbbdcaaabdabccddccddaacdbbddbbdabdabcabdddcdcbbbacadaadcbbcddbaadcbabdacaccbdababdacaccbdddbdababacaabcaabadbbbcdcdddacaabcaababddccbadbadbbdcccbadbadbbdcaadbdabccddccddaacdbbddbbdabdabccbdddcdcbbbacaddadcbbcddbaadcbdbdacaccbdababdacaccbdddbdababdcaabcaabadbbbcdcdddacaabcaabadddccbadbadbbdcccbadbadbbdcaadadabccddccddaacdbbddbbdabdabccadddcdcbbbacaddddcbbcddbaadcbdadacaccbdababdacaccbdddbdababdaaabcaabadbbbcdcdddacaabcaabadbdccbadbadbbdcccbadbadbbdcaadadabccddccddaacdbbddbbdabdabccabddcdcbbbacadddccbbcddbaadcbdaaacaccbdababdacaccbdddbdababdacabcaabadbbbcdcdddacaabcaabadbbccbadbadbbdcccbadbadbbdcaadadbbccddccddaacdbbddbbdabdabccabcdcdcbbbacadddcdbbcddbaadcbdaadcaccbdababdacaccbdddbdababdacabcaabadbbbcdcdddacaabcaabadbbbcbadbadbbdcccbadbadbbdcaadadbaccddccddaacdbbddbbdabdabccabcccdcbbbacadddcdcbcddbaadcbdaadcaccbdababdacaccbdddbdababdacaccaabadbbbcdcdddacaabcaabadbbbcbadbadbbdcccbadbadbbdcaadadbadcddccddaacdbbddbbdabdabccabccddcbbbacadddcdcacddbaadcbdaadcbccbdababdacaccbdddbdababdacaccaabadbbbcdcdddacaabcaabadbbbcdadbadbbdcccbadbadbbdcaadadbadbddccddaacdbbddbbdabdabccabccddcbbbacadddcdcabddbaadcbdaadcbbcbdababdacaccbdddbdababdacaccbabadbbbcdcdddacaabcaabadbbbcdcdbadbbdcccbadbadbbdcaadadbadbbdccddaacdbbddbbdabdabccabccddabbbacadddcdcabadbaadcbdaadcbbcbdababdacaccbdddbdababdacaccbdbadbbbcdcdddacaabcaabadbbbcdcdbadbbdcccbadbadbbdcaadadbadbbdccddaacdbbddbbdabdabccabccddaabbacadddcdcabaabaadcbdaadcbbcddababdacaccbdddbdababdacaccbddadbbbcdcdddacaabcaabadbbbcdcddadbbdcccbadbadbbdcaadadbadbbdccddaacdbbddbbdabdabccabccddaacbacadddcdcabaacaadcbdaadcbbcddababdacaccbdddbdababdacaccbddddbbbcdcdddacaabcaabadbbbcdcddddbbdcccbadbadbbdcaadadbadbbdcaddaacdbbddbbdabdabccabccddaacdacadddcdcabaacaadcbdaadcbbcddbbabdacaccbdddbdababdacaccbdddbbbbcdcdddacaabcaabadbbbcdcdddabbdcccbadbadbbdcaadadbadbbdcaadaacdbbddbbdabdabccabccddaacdbcadddcdcabaacaddcbdaadcbbcddbaabdacaccbdddbdababdacaccbdddbdbbcdcdddacaabcaabadbbbcdcdddacbdcccbadbadbbdcaadadbadbbdcaadaacdbbddbbdabdabccabccddaacdbaadddcdcabaacaddcbdaadcbbcddbaabdacaccbdddbdababdacaccbdddbddbcdcdddacaabcaabadbbbcdcdddacadcccbadbadbbdcaadadbadbbdcaaddacdbbddbbdabdabccabccddaacdbacdddcdcabaacadddbdaadcbbcddbaaddacaccbdddbdababdacaccbdddbddacdcdddacaabcaabadbbbcdcdddacaacccbadbadbbdcaadadbadbbdcaaddccdbbddbbdabdabccabccddaacdbacdddcdcabaacadddbdaadcbbcddbaadcacaccbdddbdababdacaccbdddbddacdcdddacaabcaabadbbbcdcdddacaabccbadbadbbdcaadadbadbbdcaaddcbdbbddbbdabdabccabccddaacdbacdbdcdcabaacadddbbaadcbbcddbaadcbcaccbdddbdababdacaccbdddbddacacdddacaabcaabadbbbcdcdddacaabccbadbadbbdcaadadbadbbdcaaddcbabbddbbdabdabccabccddaacdbacdbbcdcabaacadddbbdadcbbcddbaadcbdaccbdddbdababdacaccbdddbddacacdddacaabcaabadbbbcdcdddacaabcabadbadbbdcaadadbadbbdcaaddcbadbddbbdabdabccabccddaacdbacdbbddcabaacadddbbdadcbbcddbaadcbdaccbdddbdababdacaccbdddbddacaccddacaabcaabadbbbcdcdddacaabcaaadbadbbdcaadadbadbbdcaaddcbadbddbbdabdabccabccddaacdbacdbbdacabaacadddbbdabcbbcddbaadcbdaacbdddbdababdacaccbdddbddacaccbdacaabcaabadbbbcdcdddacaabcaabdbadbbdcaadadbadbbdcaaddcbadbbdbbdabdabccabccddaacdbacdbbdababaacadddbbdababbcddbaadcbdaadbdddbdababdacaccbdddbddacaccbdacaabcaabadbbbcdcdddacaabcaababadbbdcaadadbadbbdcaaddcbadbbdbbdabdabccabccddaacdbacdbbdabdbaacadddbbdabaabcddbaadcbdaadcdddbdababdacaccbdddbddacaccbddcaabcaabadbbbcdcdddacaabcaabadadbbdcaadadbadbbdcaaddcbadbbdcbdabdabccabccddaacdbacdbbdabdaaacadddbbdabaaccddbaadcbdaadcbddbdababdacaccbdddbddacaccbdddaabcaabadbbbcdcdddacaabcaabadbdbbdcaadadbadbbdcaaddcbadbbdcadabdabccabccddaacdbacdbbdabdabacadddbbdabaacaddbaadcbdaadcbbdbdababdacaccbdddbddacaccbdddbabcaabadbbbcdcdddacaabcaabadbbbbdcaadadbadbbdcaaddcbadbbdcaaabdabccabccddaacdbacdbbdabdabbcadddbbdabaacaddbaadcbdaadcbbcbdababdacaccbdddbddacaccbdddbdbcaabadbbbcdcdddacaabcaabadbbbbdcaadadbadbbdcaaddcbadbbdcaadbdabccabccddaacdbacdbbdabdabbdadddbbdabaacadcbaadcbdaadcbbcddababdacaccbdddbddacaccbdddbdacaabadbbbcdcdddacaabcaabadbbbcdcaadadbadbbdcaaddcbadbbdcaadddabccabccddaacdbacdbbdabdabbdadddbbdabaacadcbaadcbdaadcbbcddababdacaccbdddbddacaccbdddbdabaabadbbbcdcdddacaabcaabadbbbcdcaadadbadbbdcaaddcbadbbdcaaddcabccabccddaacdbacdbbdabdabbdabddbbdabaacadcbbadcbdaadcbbcddbbabdacaccbdddbddacaccbdddbdabaabadbbbcdcdddacaabcaabadbbbcdcaadadbadbbdcaaddcbadbbdcaaddccbccabccddaacdbacdbbdabdabbdabcdbbdabaacadcbbbdcbdaadcbbcddbaabdacaccbdddbddacaccbdddbdababbadbbbcdcdddacaabcaabadbbbcdcdadadbadbbdcaaddcbadbbdcaaddccbccabccddaacdbacdbbdabdabbdabccbbdabaacadcbbbdcbdaadcbbcddbaabdacaccbdddbddacaccbdddbdababdadbbbcdcdddacaabcaabadbbbcdcdddadbadbbdcaaddcbadbbdcaaddccbdcabccddaacdbacdbbdabdabbdabccdbdabaacadcbbbdabdaadcbbcddbaaddacaccbdddbddacaccbdddbdababdadbbbcdcdddacaabcaabadbbbcdcdddadbadbbdcaaddcbadbbdcaaddccbdbabccddaacdbacdbbdabdabbdabccdddabaacadcbbbdabdaadcbbcddbaadcacaccbdddbddacaccbdddbdababdacbbbcdcdddacaabcaabadbbbcdcdddadbadbbdcaaddcbadbbdcaaddccbdbbbccddaacdbacdbbdabdabbdabccddaabaacadcbbbdabaaadcbbcddbaadcbcaccbdddbddacaccbdddbdababdacabbcdcdddacaabcaabadbbbcdcdddacbadbbdcaaddcbadbbdcaaddccbdbbdccddaacdbacdbbdabdabbdabccddaabaacadcbbbdabadadcbbcddbaadcbdaccbdddbddacaccbdddbdababdacacbcdcdddacaabcaabadbbbcdcdddacaadbbdcaaddcbadbbdcaaddccbdbbdccddaacdbacdbbdabdabbdabccddaacaacadcbbbdabadddcbbcddbaadcbdaccbdddbddacaccbdddbdababdacacccdcdddacaabcaabadbbbcdcdddacaadbbdcaaddcbadbbdcaaddccbdbbdcaddaacdbacdbbdabdabbdabccddaacdacadcbbbdabaddccbbcddbaadcbdaacbdddbddacaccbdddbdababdacaccbdcdddacaabcaabadbbbcdcdddacaabbbdcaaddcbadbbdcaaddccbdbbdcaadaacdbacdbbdabdabbdabccddaacdacadcbbbdabaddcdbbcddbaadcbdaadbdddbddacaccbdddbdababdacaccbdcdddacaabcaabadbbbcdcdddacaabcbdcaaddcbadbbdcaaddccbdbbdcaadaacdbacdbbdabdabbdabccddaacdaaadcbbbdabaddcdcbcddbaadcbdaadcdddbddacaccbdddbdababdacaccbdddddacaabcaabadbbbcdcdddacaabcadcaaddcbadbbdcaaddccbdbbdcaaddacdbacdbbdabdabbdabccddaacdaacdcbbbdabaddcdcbcddbaadcbdaadcbddbddacaccbdddbdababdacaccbdddddacaabcaabadbbbcdcdddacaabcaacaaddcbadbbdcaaddccbdbbdcaaddccdbacdbbdabdabbdabccddaacdaacdcbbbdabaddcdcbbddbaadcbdaadcbbdbddacaccbdddbdababdacaccbdddbdacaabcaabadbbbcdcdddacaabcaabaaddcbadbbdcaaddccbdbbdcaaddccdbacdbbdabdabbdabccddaacdaacdbbbbdabaddcdcbbbdbaadcbdaadcbbcbddacaccbdddbdababdacaccbdddbdacaabcaabadbbbcdcdddacaabcaabaaddcbadbbdcaaddccbdbbdcaaddccbbacdbbdabdabbdabccddaacdaacdbbbbdabaddcdcbbbdbaadcbdaadcbbcdddacaccbdddbdababdacaccbdddbdacaabcaabadbbbcdcdddacaabcaabadddcbadbbdcaaddccbdbbdcaaddccbaacdbbdabdabbdabccddaacdaacdbbdbdabaddcdcbbbdcaadcbdaadcbbcdddacaccbdddbdababdacaccbdddbdabaabcaabadbbbcdcdddacaabcaabadbdcbadbbdcaaddccbdbbdcaaddccbadcdbbdabdabbdabccddaacdaacdbbdadabaddcdcbbbdcaadcbdaadcbbcddbacaccbdddbdababdacaccbdddbdabaabcaabadbbbcdcdddacaabcaabadbbcbadbbdcaaddccbdbbdcaaddccbadbdbbdabdabbdabccddaacdaacdbbdababaddcdcbbbdcaddcbdaadcbbcddbacaccbdddbdababdacaccbdddbdababbcaabadbbbcdcdddacaabcaabadbbbbadbbdcaaddccbdbbdcaaddccbadbdbbdabdabbdabccddaacdaacdbbdabdbaddcdcbbbdcaddcbdaadcbbcddbaaaccbdddbdababdacaccbdddbdababdcaabadbbbcdcdddacaabcaabadbbbcadbbdcaaddccbdbbdcaaddccbadbdcbdabdabbdabccddaacdaacdbbdabddaddcdcbbbdcadddbdaadcbbcddbaadccbdddbdababdacaccbdddbdababdcaabadbbbcdcdddacaabcaabadbbbcddbbdcaaddccbdbbdcaaddccbadbdcadabdabbdabccddaacdaacdbbdabddaddcdcbbbdcadddcdaadcbbcddbaadccbdddbdababdacaccbdddbdababdcbabadbbbcdcdddacaabcaabadbbbcdcbbdcaaddccbdbbdcaaddccbadbdcaaabdabbdabccddaacdaacdbbdabddabdcdcbbbdcadddcdaadcbbcddbaadcbbdddbdababdacaccbdddbdababdcbdbadbbbcdcdddacaabcaabadbbbcdcdbdcaaddccbdbbdcaaddccbadbdcaadbdabbdabccddaacdaacdbbdabddabdcdcbbbdcadddcdcadcbbcddbaadcbddddbdababdacaccbdddbdababdcbddadbbbcdcdddacaabcaabadbbbcdcdddcaaddccbdbbdcaaddccbadbdcaadddabbdabccddaacdaacdbbdabddabdadcbbbdcadddcdcbdcbbcddbaadcbdaddbdababdacaccbdddbdababdcbddddbbbcdcdddacaabcaabadbbbcdcdddcaaddccbdbbdcaaddccbadbdcaaddcabbdabccddaacdaacdbbdabddabdabcbbbdcadddcdcbbcbbcddbaadcbdaadbdababdacaccbdddbdababdcbdddbbbbcdcdddacaabcaabadbbbcdcdddaaaddccbdbbdcaaddccbadbdcaaddccbbdabccddaacdaacdbbdabddabdabcbbbdcadddcdcbbabbcddbaadcbdaadbdababdacaccbdddbdababdcbdddbdbbcdcdddacaabcaabadbbbcdcdddacaddccbdbbdcaaddccbadbdcaaddccbbdabccddaacdaacdbbdabddabdabccbbdcadddcdcbbaabcddbaadcbdaadcdababdacaccbdddbdababdcbdddbdabcdcdddacaabcaabadbbbcdcdddacaddccbdbbdcaaddccbadbdcaaddccbadabccddaacdaacdbbdabddabdabccdbdcadddcdcbbaaccddbaadcbdaadcbababdacaccbdddbdababdcbdddbdabcdcdddacaabcaabadbbbcdcdddacaadccbdbbdcaaddccbadbdcaaddccbadabccddaacdaacdbbdabddabdabccdddcadddcdcbbaacaddbaadcbdaadcbbbabdacaccbdddbdababdcbdddbdabadcdddacaabcaabadbbbcdcdddacaabccbdbbdcaaddccbadbdcaaddccbadbbccddaacdaacdbbdabddabdabccddacadddcdcbbaacaddbaadcbdaadcbbcabdacaccbdddbdababdcbdddbdababcdddacaabcaabadbbbcdcdddacaabccbdbbdcaaddccbadbdcaaddccbadbaccddaacdaacdbbdabddabdabccddacadddcdcbbaacaddbaadcbdaadcbbcdbdacaccbdddbdababdcbdddbdababddddacaabcaabadbbbcdcdddacaabcabdbbdcaaddccbadbdcaaddccbadbadcddaacdaacdbbdabddabdabccddacddddcdcbbaacadddaadcbdaadcbbcdddacaccbdddbdababdcbdddbdababdaddacaabcaabadbbbcdcdddacaabcaadbbdcaaddccbadbdcaaddccbadbadaddaacdaacdbbdabddabdabccddacddddcdcbbaacadddcadcbdaadcbbcddbacaccbdddbdababdcbdddbdababdacdacaabcaabadbbbcdcdddacaabcaabbbdcaaddccbadbdcaaddccbadbadaadaacdaacdbbdabddabdabccddacddadcdcbbaacadddcddcbdaadcbbcddbacaccbdddbdababdcbdddbdababdacaacaabcaabadbbbcdcdddacaabcaababdcaaddccbadbdcaaddccbadbadaadaacdaacdbbdabddabdabccddacddaacdcbbaacadddcddcbdaadcbbcddbaaaccbdddbdababdcbdddbdababdacaccaabcaabadbbbcdcdddacaabcaabaddcaaddccbadbdcaaddccbadbadaaddacdaacdbbdabddabdabccddacddaacdcbbaacadddcddabdaadcbbcddbaadccbdddbdababdcbdddbdababdacaccaabcaabadbbbcdcdddacaabcaabadbcaaddccbadbdcaaddccbadbadaaddccdaacdbbdabddabdabccddacddaacdcbbaacadddcddabdaadcbbcddbaadccbdddbdababdcbdddbdababdacaccbabcaabadbbbcdcdddacaabcaabadbbaaddccbadbdcaaddccbadbadaaddccdaacdbbdabddabdabccddacddaacdbbbaacadddcddabaaadcbbcddbaadcbbdddbdababdcbdddbdababdacaccbdbcaabadbbbcdcdddacaabcaabadbbbaddccbadbdcaaddccbadbadaaddccbaacdbbdabddabdabccddacddaacdbbbaacadddcddabaaadcbbcddbaadcbddddbdababdcbdddbdababdacaccbddcaabadbbbcdcdddacaabcaabadbbbcddccbadbdcaaddccbadbadaaddccbaacdbbdabddabdabccddacddaacdbbdaacadddcddabaacdcbbcddbaadcbdaddbdababdcbdddbdababdacaccbdddaabadbbbcdcdddacaabcaabadbbbcddccbadbdcaaddccbadbadaaddccbadcdbbdabddabdabccddacddaacdbbdaacadddcddabaacacbbcddbaadcbdaadbdababdcbdddbdababdacaccbdddbabadbbbcdcdddacaabcaabadbbbcdcccbadbdcaaddccbadbadaaddccbadbdbbdabddabdabccddacddaacdbbdabcadddcddabaacadbbcddbaadcbdaadbdababdcbdddbdababdacaccbdddbdbadbbbcdcdddacaabcaabadbbbcdcdcbadbdcaaddccbadbadaaddccbadbabbdabddabdabccddacddaacdbbdabbadddcddabaacaddbcddbaadcbdaadcdababdcbdddbdababdacaccbdddbdaadbbbcdcdddacaabcaabadbbbcdcddbadbdcaaddccbadbadaaddccbadbadbdabddabdabccddacddaacdbbdabbddddcddabaacaddbcddbaadcbdaadcbababdcbdddbdababdacaccbdddbdabdbbbcdcdddacaabcaabadbbbcdcdddadbdcaaddccbadbadaaddccbadbadbdabddabdabccddacddaacdbbdabbdaddcddabaacaddbbddbaadcbdaadcbbbabdcbdddbdababdacaccbdddbdababbbcdcdddacaabcaabadbbbcdcdddadbdcaaddccbadbadaaddccbadbadbbabddabdabccddacddaacdbbdabbdabdcddabaacaddbbbdbaadcbdaadcbbcabdcbdddbdababdacaccbdddbdababbbcdcdddacaabcaabadbbbcdcdddacbdcaaddccbadbadaaddccbadbadbbdbddabdabccddacddaacdbbdabbdabdcddabaacaddbbbdbaadcbdaadcbbcdbdcbdddbdababdacaccbdddbdababdbcdcdddacaabcaabadbbbcdcdddacadcaaddccbadbadaaddccbadbadbbddddabdabccddacddaacdbbdabbdabdaddabaacaddbbbdaaadcbdaadcbbcdddcbdddbdababdacaccbdddbdababdacdcdddacaabcaabadbbbcdcdddacaacaaddccbadbadaaddccbadbadbbddcdabdabccddacddaacdbbdabbdabdabdabaacaddbbbdabadcbdaadcbbcddbcbdddbdababdacaccbdddbdababdacdcdddacaabcaabadbbbcdcdddacaabaaddccbadbadaaddccbadbadbbddccabdabccddacddaacdbbdabbdabdabcabaacaddbbbdabadcbdaadcbbcddbcbdddbdababdacaccbdddbdababdacacdddacaabcaabadbbbcdcdddacaabcaddccbadbadaaddccbadbadbbddccbbdabccddacddaacdbbdabbdabdabccbaacaddbbbdabaacbdaadcbbcddbcddddbdababdacaccbdddbdababdacacdddacaabcaabadbbbcdcdddacaabcaddccbadbadaaddccbadbadbbddccbadabccddacddaacdbbdabbdabdabccdaacaddbbbdabaacbdaadcbbcddbcddddbdababdacaccbdddbdababdacaccddacaabcaabadbbbcdcdddacaabcaadccbadbadaaddccbadbadbbddccbadabccddacddaacdbbdabbdabdabccddacaddbbbdabaaccdaadcbbcddbcddbdbdababdacaccbdddbdababdacaccbdacaabcaabadbbbcdcdddacaabcaabccbadbadaaddccbadbadbbddccbadbbccddacddaacdbbdabbdabdabccddccaddbbbdabaaccdaadcbbcddbcddbabdababdacaccbdddbdababdacaccbdacaabcaabadbbbcdcdddacaabcaabacbadbadaaddccbadbadbbddccbadbaccddacddaacdbbdabbdabdabccddccaddbbbdabaaccdcadcbbcddbcddbaadababdacaccbdddbdababdacaccbdacaabcaabadbbbcdcdddacaabcaabadbadbadaaddccbadbadbbddccbadbadcddacddaacdbbdabbdabdabccddccdddbbbdabaaccdcbdcbbcddbcddbaadababdacaccbdddbdababdacaccbdabaabcaabadbbbcdcdddacaabcaabadbadbadaaddccbadbadbbddccbadbadbddacddaacdbbdabbdabdabccddccdddbbbdabaaccdcbbcbbcddbcddbaadcbabdacaccbdddbdababdacaccbdabaabcaabadbbbcdcdddacaabcaabadbbdbadaaddccbadbadbbddccbadbadbbdacddaacdbbdabbdabdabccddccddabbbdabaaccdcbbbbbcddbcddbaadcbabdacaccbdddbdababdacaccbdababbcaabadbbbcdcdddacaabcaabadbbbbadaaddccbadbadbbddccbadbadbbdacddaacdbbdabbdabdabccddccddaabbdabaaccdcbbbdbcddbcddbaadcbdbdacaccbdddbdababdacaccbdababdcaabadbbbcdcdddacaabcaabadbbbcadaaddccbadbadbbddccbadbadbbdccddaacdbbdabbdabdabccddccddaacbdabaaccdcbbbdacddbcddbaadcbdadacaccbdddbdababdacaccbdababdaaabadbbbcdcdddacaabcaabadbbbcddaaddccbadbadbbddccbadbadbbdccddaacdbbdabbdabdabccddccddaacddabaaccdcbbbdabddbcddbaadcbdaaacaccbdddbdababdacaccbdababdacabadbbbcdcdddacaabcaabadbbbcdcaaddccbadbadbbddccbadbadbbdcccdaacdbbdabbdabdabccddccddaacdbabaaccdcbbbdabddbcddbaadcbdaadcaccbdddbdababdacaccbdababdacabadbbbcdcdddacaabcaabadbbbcdcdaddccbadbadbbddccbadbadbbdcccbaacdbbdabbdabdabccddccddaacdbbbaaccdcbbbdabddbcddbaadcbdaadcaccbdddbdababdacaccbdababdacacadbbbcdcdddacaabcaabadbbbcdcddddccbadbadbbddccbadbadbbdcccbaacdbbdabbdabdabccddccddaacdbbdaaccdcbbbdabdddcddbaadcbdaadcbccbdddbdababdacaccbdababdacaccdbbbcdcdddacaabcaabadbbbcdcddddccbadbadbbddccbadbadbbdcccbadcdbbdabbdabdabccddccddaacdbbddaccdcbbbdabdddc";
	private static final Integer TOTAL_LIVROS = 2;
	private static final String REGEX_EXPORT_VOWELS = "[^a-zA-Z]";

	static ArrayList<ArrayList<Character>> answers = new ArrayList<>();

	public static void main(String[] args) {
		readSample(precalc());
	}

	/**
	 * Metodo responsavel pelo calculo do SAMPLE, ele é responsavel por gerar o
	 * gabatito correto para cada livro
	 * Os livros da TecToy eram agrupados por 5 blocos de 30 questões contento um
	 * total de 150 perguntas.
	 * Ex: Livros [011, 012, 013, 114 e 115] ou [021, 022, 023, 124 e 125] ...
	 * Existe um 6º livros que não é calculado aqui era apenas um validação no caso
	 * dos livros de 011 a 015 seria o [016]
	 */
	public static String precalc() {

		var sample = SAMPLE;

		// esse bloco cria a lista dos livros que por sua vez terá 150 perguntas cada
		for (int b = 0; b < TOTAL_LIVROS; b++) {
			answers.add(new ArrayList<>());
		}

		// essas 150 letras repesentam as repostas corretas que serão trocadas a cada
		// ciclo
		String pattern1 = "dbaadcbdaadcbbc"
				+ "bdddbdababdacac"
				+ "cbdababdacaccbd"
				+ "bbcdcdddacaabca"
				+ "abadbbbcdcdddac"
				+ "cbadbadbbddccba"
				+ "dbadbbdcccbadba"
				+ "bbdabbdabdabccd"
				+ "dccddaacdbbddbb"
				+ "cdcbbbdabdddcdc";

		// No seu código, o pattern2 é uma string contendo 588 dígitos (valores de 0 a
		// 3). Ele é consumido 6 vezes por livro (quando a questão é a 15ª de cada bloco
		// ou a última: q % 30 == 14 || q == 149). Como o código roda para 98 livros (do
		// b = 1 até o 98), o algoritmo consome exatamente os 588 dígitos (6 * 98 =
		// 588).
		// O valor numérico v extraído do pattern2 serve como um "fator de rotação"
		// (shift) para as letras (a, b, c, d)
		String pattern2 = "22221202301023123110332032313302" + "03022121320233203323333220221221"
				+ "30303330010113102300312222030031" + "22201303322312111332102302332023"
				+ "12033033201101201022100330112212" + "31101032132131211313212111330313"
				+ "23120203032010023131303302312120" + "03233301131332001130130102322321"
				+ "00101020113320201200223033300200" + "20332303233320232301303322112030"
				+ "33000131223323032222211303211222" + "01022012130321201023122111120300"
				+ "31213021320123211301301322230130" + "22030130333312012220221103001133"
				+ "10031131131230212010110223103300" + "32322123132020333001212020032303"
				+ "10302221221023033011310303012012" + "12012031321213323020123321303210"
				+ "013020120331";

		for (int b = 0; b < TOTAL_LIVROS; b++) {
			// Esse bloco serve para montar o primeiro livro da pilha que não tem nenhuma
			// alteração em cima do pattern1 que ja existe
			if (b == 0) {
				for (int q = 0; q < 150; q++) {
					answers.get(b).add(pattern1.charAt(q));
				}
				// quando o primeiro livro termina ele pula para o proximo ciclo e nunca mas
				// volta para esse bloco inicial presente no pattern1 onde ele reagrupa os
				// valores mudando as posições do gabarito.
				continue;
			}
			// Esse bloco guarda a primeira posição de cada ciclo por que como os valores
			// vai sendo deslocados precisa recolocalo na ultima posição para manter o
			// contexto ciclico
			char first = pattern1.charAt(0);
			for (int q = 0; q < 150; q++) {
				int v = 0;
				if (q % 30 == 14 || q == 149) {
					// Esse bloco sempre pega a primeira posição do pattern2
					v = Integer.parseInt(String.valueOf(pattern2.charAt(0)));
					// uma vez que a primeira posição do partner2 é recuperada ela é descartada
					// definitivamente do ciclo isso vai acontecer ate concliur todos os livros que
					// o limite maximo é de 99
					pattern2 = pattern2.substring(1);
				}

				char prev;
				if (q == 149) {
					// Aqui é onde ele recoloca a primeira posição salva anteriormente para colocala
					// na ultima posição do total de 150 questões por livro
					prev = first;
				} else {
					// caso nao seja a ultima posicão do total de 150 ele ja puxa a proxima como se
					// foce a primeira possição e assim por diante
					prev = pattern1.charAt(q + 1);
				}

				// o X representa a nova sequencia de respostas que vai sendo formada ate da
				// posição 0 ate a posição 149 de forma incremental ate o total de 150 questões
				var x = pattern1.substring(0, q);

				// o y representa o valor da posição atual baseado no deslocamento v mais o
				// valor da posição anterior
				// Esse algoritimo adiciona uma "aleatoriedade controlada" ao gabarito,
				// rotacionando a resposta correta em 0, 1, 2 ou 3 posições graças a letra V.
				var y = (char) ('a' + (prev - 'a' + v) % 4);

				// o z representa a nova sequencia de respostas que vai sendo formada ate da
				// posição 0 ate a posição 149 de forma incremental ate o total de 150 questões
				var z = pattern1.substring(q + 1);

				// Nesse momento ele somar as string da posições ja calculadas pelo x + a nova
				// letra da posição atual y + o restante da string z
				pattern1 = x + y + z;
			}

			// Uma vez que a nova configuração das questão foram montatas ele passa para
			// livro em questão
			for (int q = 0; q < 150; q++) {
				answers.get(b).add(pattern1.charAt(q));
			}
		}

		// Esse trecho imprime o novo Sample recalculado
		return answers.toString().replaceAll(REGEX_EXPORT_VOWELS, "");
	}

	/**
	 * Esse método tem como objetivo ler o SAMPLE e montar o gabarito de forma que
	 * possamos montar futuros livros, exemplo: 011--001--d (LLB--QQQ--A)
	 * 
	 * LL: representa o livro que estamos lendo que vai de 01 a 99, inclusive a
	 * Tectoy variava entre os livros para melhor adequação das respostas ao tema e
	 * para as crianças não decorarem as ordens das respostas.
	 * B: Representa o bloco do livro dependendo de quem publica pode ser chamado de
	 * sessão ou capitulo que varia de 1 a 5
	 * QQQ: Representa o numero da questão Exemplo: no bloco 011 a questão vai de
	 * 001 a 030 já no bloco 012 as questões vão de 31 a 60 e assim por diante
	 * inclusive os números de 001 a 150 são representado no painel do Pense Bem.
	 * A: Representa a letra correta da questão. Obs. as ordem das respostas variam
	 * de acordo com o livro escolhido de 01 a 99.
	 */
	private static void readSample(String sample) {
		List<Character> samples = new ArrayList<>();

		for (char character : sample.toCharArray()) {
			samples.add((char) character);
		}

		Map<Integer, Character> m = new HashMap<>();
		for (int book = 1; book <= TOTAL_LIVROS; book++) {
			for (int question = 1; question <= 150; question++) {
				char a = samples.remove(0);
				m.put(book * 1000 + question, a);
				System.out.println(String.format("%02d", book) + getSession(question) + "--"
						+ String.format("%03d", question) + "--" + a);
			}
		}
	}

	/**
	 * Método responsável pelo retorno dos blocos para cada numero da questão
	 */
	private static String getSession(int question) {
		if (question <= 30) {
			return "1";
		} else if (question >= 31 && question <= 60) {
			return "2";
		} else if (question >= 61 && question <= 90) {
			return "3";
		} else if (question >= 91 && question <= 120) {
			return "4";
		} else if (question >= 121) {
			return "5";
		} else {
			return "";
		}
	}

}
