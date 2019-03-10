import java.util.ArrayList;

public class Vowels {

//    public static void main(String[] args) {
//        ArrayList<String> combos = new ArrayList<>();
//        combos = vowelsGenerator();
//
//        for (int i = 0; i < combos.size(); i++) {
//            System.out.println(combos.get(i));
//        }
//        System.out.println(combos.size());
//    }

    static String[] vowelsGenerator(){
        String[] vowels = {"a", "e", "i", "o", "u"};

        String combinations[] = new String[25];
        int combo_count = 0;

        for (int i = 0; i < vowels.length; i++) {
            for (int j = 0; j < vowels.length; j++) {
                combinations[combo_count++] = (vowels[i] + vowels[j]);
            }

        }
        return combinations;
    }

    static int comboHunter(String target) {
        String combos[] = new String[25];
        combos = vowelsGenerator();

        int combo_count = 0;

        for (int i = 0; i < combos.length ; i++) {
            if (target.contains(combos[i])) {
                combo_count++;
            }
        }

        return combo_count;
    }
}
