import java.util.*;

/**
 * Author  :   ChenKang
 * Time    :   2019/8/4
 * Info    :    贴纸拼词
 */

public class Solution_691 {
    String target;
    List<Sticker> stickersList = new ArrayList<>();
    Set<Character> characters = new HashSet<>();
    Map<Character,Integer> letterNumbers = new HashMap<>();
    Sticker[] stickersArray;
    /*
    todo 超出时间限制，算法思路正确。
     */
    public static void main(String[] args) {
        Solution_691 s = new Solution_691();
        String[] stickers = {"a","enemy","material","whose","twenty","describe","magnet","put","hundred","discuss"};
        String target = "separatewhich";
        long start = System.currentTimeMillis();
        System.out.println(s.minStickers(stickers,target));
        System.out.println(System.currentTimeMillis()-start);
    }

    public int minStickers(String[] stickers, String target) {
        long start = System.currentTimeMillis();
        char[] letters = target.toCharArray();
        Set<Character> lettersSet = new HashSet<>();
        for (int i=0;i<letters.length;i++){
            lettersSet.add(letters[i]);
        }
        for (int i=0;i<stickers.length;i++){
            stickersList.add(new Sticker(stickers[i],lettersSet));
        }
        for (Sticker sticker : stickersList){
            for (Character character : sticker.characters.keySet()){
                characters.add(character);
            }
        }
        System.out.println(System.currentTimeMillis()-start);
        for (int i=0;i<letters.length;i++){
            if (characters.contains(letters[i]) == false){
                return -1;
            }
        }
        for (int i=0;i<letters.length;i++){
            if (letterNumbers.get(letters[i]) == null){
                letterNumbers.put(letters[i],1);
            } else {
                int num = letterNumbers.get(letters[i]);
                letterNumbers.put(letters[i],num+1);
            }
        }
        System.out.println(System.currentTimeMillis()-start);
        Map<Sticker,Integer> stickerIntegerMap = new HashMap<>();
        for (Sticker sticker : stickersList){
            stickerIntegerMap.put(sticker,getSameCharacterNumber(sticker,letterNumbers));
        }
        List<Sticker> sortSticker = new ArrayList<>();
        while (sortSticker.size() < stickerIntegerMap.size()){
            Sticker removerSticker = null;
            int max = Integer.MIN_VALUE;
            for (Sticker sticker : stickerIntegerMap.keySet()){
                if (max < stickerIntegerMap.get(sticker)){
                    max = stickerIntegerMap.get(sticker);
                    removerSticker = sticker;
                }
            }
            sortSticker.add(removerSticker);
            stickerIntegerMap.put(removerSticker,Integer.MIN_VALUE);
        }
        stickersArray = new Sticker[sortSticker.size()];
        for (int i=0;i<sortSticker.size();i++){
            stickersArray[i] = sortSticker.get(i);
        }
        System.out.println(System.currentTimeMillis()-start);
        return getMinStickerNumber(letterNumbers)-1;
    }

    public int getMinStickerNumber(Map<Character,Integer> letterNumbers){
        if (letterNumbers.isEmpty()){
            return 1;
        }
        int min = 100;
        for (int i=0;i<stickersArray.length;i++){
            Map<Character,Integer> newLetterNumbers = new HashMap<>();
            for (Character character : letterNumbers.keySet()){
                newLetterNumbers.put(character,letterNumbers.get(character));
            }
            Sticker sticker = stickersArray[i];
            boolean isRemoveLetter = false;
            for (Character character : sticker.characters.keySet()){
                if (newLetterNumbers.get(character) == null){
                    continue;
                } else {
                    int oldNum = newLetterNumbers.get(character);
                    newLetterNumbers.put(character,oldNum-sticker.characters.get(character));
                    if (newLetterNumbers.get(character) <= 0){
                        newLetterNumbers.remove(character);
                    }
                    isRemoveLetter = true;
                }
            }
            if (isRemoveLetter){
                min = Math.min(min,getMinStickerNumber(newLetterNumbers));
            }
        }
        return min+1;
    }

    public int getSameCharacterNumber(Sticker sticker,Map<Character,Integer> letter){
        int count = 0;
        for (Character character : letter.keySet()){
            if (sticker.characters.get(character) == null){
                continue;
            } else {
                if (sticker.characters.get(character) <= letter.get(character)){
                    count += sticker.characters.get(character);
                } else {
                    count += letter.get(character);
                }
            }
        }
        return count;
    }

}

class Sticker{
    Map<Character,Integer> characters = new HashMap<>();
    Set<Character> letters = new HashSet<>();

    public Sticker(String string,Set<Character> letters) {
        this.letters = letters;
        char[] chars = string.toCharArray();
        for (int i=0;i<chars.length;i++){
            if (letters.contains(chars[i])){
                if (characters.get(chars[i]) == null){
                    characters.put(chars[i],1);
                } else {
                    int num = characters.get(chars[i]);
                    characters.put(chars[i],num+1);
                }
            }
        }
    }
}
