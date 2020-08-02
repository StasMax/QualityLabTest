package helpers;

import java.util.Random;

/**
 * Класс для работы над созданием строк.
 */
public final class StringUtils {

    /**
     * Приватный конструктор.
     */
    private StringUtils() {
    }

    /**
     * Метод создания случайной строки
     * @param length длина строки
     * @return случайная строка
     */
    public static String getRandomCyrillicString(final int length) {
        String symbols =
                "ЙЦУКЕНГШЩЗХЪЫФВАПРОЛДЖЭЯЧСМИТЬБЮйцукенгшщзхъфывапролджэячсмитьбю";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            stringBuilder.append(symbols
                    .charAt(random.nextInt(symbols.length())));
        }
        return stringBuilder.toString();
    }

    /**
     * Метод создания случайного текста
     * @param wordsCount кол-во слов в тексте
     * @return случайный текст
     */
    public static String getRandomText(final int wordsCount) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < wordsCount; i++) {
            stringBuilder.append(getRandomCyrillicString(new Random().nextInt(13) + 3)).append(" ");
        }
        return stringBuilder.toString();
    }


}

