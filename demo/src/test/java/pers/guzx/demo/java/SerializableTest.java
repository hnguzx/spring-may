package pers.guzx.demo.java;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import pers.guzx.demo.entity.vo.CountryVO;

import java.io.*;

@Slf4j
public class SerializableTest {

    @Test
    void testSerializable() throws IOException {
        CountryVO countryVO = new CountryVO();
        countryVO.setCode(20002);
        countryVO.setName("测试");
        countryVO.setEnglishName("English");

        FileOutputStream fos = new FileOutputStream("countryVO.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(countryVO);
        oos.flush();
        oos.close();

    }

    @Test
    void testDeserializable() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("countryVO.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        CountryVO countryVO = (CountryVO) ois.readObject();
        log.info("CountryVO:{}", countryVO.toString());
    }
}
