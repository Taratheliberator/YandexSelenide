package pages;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Класс для чтения конфигурационных свойств из файла.
 * Этот класс загружает свойства из файла 'test.properties'.
 */
public class ConfigReader {
    private static final Properties properties = new Properties();

    static {

        try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("test.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Получает значение свойства по указанному ключу.
     *
     * @param key Ключ свойства, значение которого необходимо получить.
     * @return Значение свойства или null, если свойство с таким ключом не найдено.
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
