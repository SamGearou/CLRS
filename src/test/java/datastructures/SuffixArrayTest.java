package datastructures;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SuffixArrayTest {
    private final SuffixArray suffixArray = new SuffixArray();

    @Test
    public void cyclicShiftEmptyString() {
        String str = "";
        int[] arr = suffixArray.sortCyclicShifts(str + "$");
        assertThat(arr).hasSize(0);
    }

    @Test
    public void cyclicShiftLengthOne() {
        String str = "s";
        int[] arr = suffixArray.sortCyclicShifts(str + "$");
        assertThat(arr).hasSize(1);
        assertThat(arr).containsExactly(0);
    }

    @Test
    public void cyclicShiftLengthMoreThanOne() {
        String str = "banana";
        int[] arr = suffixArray.sortCyclicShifts(str + "$");
        assertThat(arr).hasSize(6);
        assertThat(arr).containsExactlyInAnyOrder(5, 3, 1, 0, 4, 2);
    }

    @Test
    public void cyclicShiftlongString() {
        String str = "ababdaebdabedeabbdddbcebaccececbccccebbcaaabaadcadccddaedaacaeddddeceedeaabbbbcbacdaeeebaabdabdbaebadcbdebaaeddcadebedeabbbcbeadbaacdebceebceeccddbeacdcecbcdbceedaeebdaeeabccccbcccbceabedaedaacdbbdbadcdbdddddcdebbcdbcabbebbeabbdccccbaaccbbcecacaebebecdcdcecdeaccccccdbbdebaaaaaaeaaeecdecedcbabedbabdedbaebeedcecebabedbceecacbdecabcebdcbecedccaeaaadbababdccedebeccecaddeabaebbeeccabeddedbeaadbcdceddceccecddbdbeeddabeddadaaaadbeedbeeeaaaeaadaebdacbdcaaabbacacccddbeaacebeeaabaadcabdbaadeaccaecbeaaabccddabbeacdecadebaecccbabeaceccaaeddedcaecddaacebcaecebebebadaceadcaccdeebbcdebcedaeaedacbeecceeebbdbdbaadeeecabdebbaaebdddeeddabcbaaebeabbbcaaeecddecbbbebecdbbbaecceeaabeeedcdecdcaeacabdcbcedcbbcaeeebaabdbaadcebbccbedbabeaddaecdbdbdccceeccaccbdcdadcccceebdabccaebcddaeeecddddacdbdbeebdabecdaeaadbadbebecbcacbbceeabbceecaabdcabebbcdecedbacbcccddcecccecbacddbeddbbbadcbdadbecceebddeacbeeabcdbbaabeabdbbbcaeeadddaeccbcdabceeabaacbeacdcbdceebeaebcceeebdcdcbeaaeeeadabbecdbadbebbecdceaeeecaaaedeceaddedbedbedbddbcbabeadddeccdaadaaeaeeadebbeabcabbdebabeedeeeccadcddaebbedadcdaebabbecedebadbdeacecdcaebcbdababcecaecbcbcdadacaebdedecaadbaaeeebcbeeedaaebbabbeeadadbacdedcbabdaabddccedbeacbecbcccdeaeeabcaeccdaaaddcdecadddabcaedccbdbbccecacbcdecbdcdcbabbeaacddaeeaabccebaaaebacebdcdcbbbdabadeebbdccabcacaacccccbadbdebecdaccabbecdabdbdaebeeadaeecbadedaebcaedeedcaacabaccbbdaccedaedddacbbbdbcaeedbcbecccdbdeddcdadacccdbcdccebdebeaeacecaaadccbbaaddbeebcbadceaebeccecabdadccddbbcbaebeaeadacaebcbbbdbcdaeadbcbdcedebabbaababaacedcbcbceaaabadbdcddadecdcebeeabaadceecaeccdeeabdbabebdcedceaeddaecedcdbccbbedbeecabaecdbba";
        int[] arr = suffixArray.sortCyclicShifts(str + "$");
        assertThat(arr).hasSize(str.length());
    }

    @Test
    public void longestRepSubstring() {
        String str = "ababdaebdabedeabbdddbcebaccececbccccebbcaaabaadcadccddaedaacaeddddeceedeaabbbbcbacdaeeebaabdabdbaebadcbdebaaeddcadebedeabbbcbeadbaacdebceebceeccddbeacdcecbcdbceedaeebdaeeabccccbcccbceabedaedaacdbbdbadcdbdddddcdebbcdbcabbebbeabbdccccbaaccbbcecacaebebecdcdcecdeaccccccdbbdebaaaaaaeaaeecdecedcbabedbabdedbaebeedcecebabedbceecacbdecabcebdcbecedccaeaaadbababdccedebeccecaddeabaebbeeccabeddedbeaadbcdceddceccecddbdbeeddabeddadaaaadbeedbeeeaaaeaadaebdacbdcaaabbacacccddbeaacebeeaabaadcabdbaadeaccaecbeaaabccddabbeacdecadebaecccbabeaceccaaeddedcaecddaacebcaecebebebadaceadcaccdeebbcdebcedaeaedacbeecceeebbdbdbaadeeecabdebbaaebdddeeddabcbaaebeabbbcaaeecddecbbbebecdbbbaecceeaabeeedcdecdcaeacabdcbcedcbbcaeeebaabdbaadcebbccbedbabeaddaecdbdbdccceeccaccbdcdadcccceebdabccaebcddaeeecddddacdbdbeebdabecdaeaadbadbebecbcacbbceeabbceecaabdcabebbcdecedbacbcccddcecccecbacddbeddbbbadcbdadbecceebddeacbeeabcdbbaabeabdbbbcaeeadddaeccbcdabceeabaacbeacdcbdceebeaebcceeebdcdcbeaaeeeadabbecdbadbebbecdceaeeecaaaedeceaddedbedbedbddbcbabeadddeccdaadaaeaeeadebbeabcabbdebabeedeeeccadcddaebbedadcdaebabbecedebadbdeacecdcaebcbdababcecaecbcbcdadacaebdedecaadbaaeeebcbeeedaaebbabbeeadadbacdedcbabdaabddccedbeacbecbcccdeaeeabcaeccdaaaddcdecadddabcaedccbdbbccecacbcdecbdcdcbabbeaacddaeeaabccebaaaebacebdcdcbbbdabadeebbdccabcacaacccccbadbdebecdaccabbecdabdbdaebeeadaeecbadedaebcaedeedcaacabaccbbdaccedaedddacbbbdbcaeedbcbecccdbdeddcdadacccdbcdccebdebeaeacecaaadccbbaaddbeebcbadceaebeccecabdadccddbbcbaebeaeadacaebcbbbdbcdaeadbcbdcedebabbaababaacedcbcbceaaabadbdcddadecdcebeeabaadceecaeccdeeabdbabebdcedceaeddaecedcdbccbbedbeecabaecdbba";
        String longestRepeating = suffixArray.longestRepeatingSubstring(str);
        assertThat(longestRepeating).isEqualTo("aeeebaabd");

        str = "moplvidmaagmsiyyrkchbyhivlqwqsjcgtumqscmxrxrvwsnjjvygrelcbjgbpounhuyealllginkitfaiviraqcycjmskrozcdqylbuejrgfnquercvghppljmojfvylcxakyjxnampmakyjbqgwbyokaybcuklkaqzawageypfqhhasetugatdaxpvtevrigynxbqodiyioapgxqkndujeranxgebnpgsukybyowbxhgpkwjfdywfkpufcxzzqiuglkakibbkobonunnzwbjktykebfcbobxdflnyzngheatpcvnhdwkkhnlwnjdnrmjaevqopvinnzgacjkbhvsdsvuuwwhwesgtdzuctshytyfugdqswvxisyxcxoihfgzxnidnfadphwumtgdfmhjkaryjxvfquucltmuoosamjwqqzeleaiplwcbbxjxxvgsnonoivbnmiwbnijkzgoenohqncjqnckxbhpvreasdyvffrolobxzrmrbvwkpdbfvbwwyibydhndmpvqyfmqjwosclwxhgxmwjiksjvsnwupraojuatksjfqkvvfroqxsraskbdbgtppjrnzpfzabmcczlwynwomebvrihxugvjmtrkzdwuafozjcfqacenabmmxzcueyqwvbtslhjeiopgbrbvfbnpmvlnyexopoahgmwplwxnxqzhucdieyvbgtkfmdeocamzenecqlbhqmdfrvpsqyxvkkyfrbyolzvcpcbkdprttijkzcrgciidavsmrczbollxbkytqjwbiupvsorvkorfriajdtsowenhpmdtvamkoqacwwlkqfdzorjtepwlemunyrghwlvjgaxbzawmikfhtaniwviqiaeinbsqidetfsdbgsydkxgwoqyztaqmyeefaihmgrbxzyheoegawthcsyyrpyvnhysynoaikwtvmwathsomddhltxpeuxettpbeftmmyrqclnzwljlpxazrzzdosemwmthcvgwtxtinffopqxbufjwsvhqamxpydcnpekqhsovvqugqhbgweaiheeicmkdtxltkalexbeftuxvwnxmqqjeyourvbdfikqnzdipmmmiltjapovlhkpunxljeutwhenrxyfeufmzipqvergdkwptkilwzdxlydxbjoxjzxwcfmznfqgoaemrrxuwpfkftwejubxkgjlizljoynvidqwxnvhngqakmmehtvykbjwrrrjvwnrteeoxmtygiiygynedvfzwkvmffghuduspyyrnftyvsvjstfohwwyxhmlfmwguxxzgwdzwlnnltpjvnzswhmbzgdwzhvbgkiddhirgljbflgvyksxgnsvztcywpvutqryzdeerlildbzmtsgnebvsjetdnfgikrbsktbrdamfccvcptfaaklmcaqmglneebpdxkvcwwpndrjqnpqgbgihsfeotgggkdbvcdwfjanvafvxsvvhzyncwlmqqsmledzfnxxfyvcmhtjreykqlrfiqlsqzraqgtmocijejneeezqxbtomkwugapwesrinfiaxwxradnuvbyssqkznwwpsbgatlsxfhpcidfgzrc";
        longestRepeating = suffixArray.longestRepeatingSubstring(str);
        assertThat(longestRepeating).isEqualTo("akyj");

        str = "abcdefghijklmnopqrstuvwxyz";
        longestRepeating = suffixArray.longestRepeatingSubstring(str);
        assertThat(longestRepeating).isEqualTo("");
    }
}