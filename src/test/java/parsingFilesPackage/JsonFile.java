package parsingFilesPackage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import model.CustomerData;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import static org.assertj.core.api.Assertions.assertThat;

public class JsonFile {
    ClassLoader cl = ParsingFiles.class.getClassLoader();
    ObjectMapper objectMapper = new ObjectMapper();

    @Disabled
    void jsonParseTest() throws Exception {
        Gson gson = new Gson();
        try (
                InputStream resource = cl.getResourceAsStream("example.json");
                InputStreamReader reader = new InputStreamReader(resource)
        ) {
            CustomerData jsonObject = gson.fromJson(reader, CustomerData.class);
            assertThat(jsonObject.firstName).isEqualTo("Joe");
            assertThat(jsonObject.lastName).isEqualTo("Jackson");
            assertThat(jsonObject.gender).isEqualTo("male");
            assertThat(jsonObject.age).isEqualTo(28);
            assertThat(jsonObject.address.streetAddress).isEqualTo(101);
            assertThat(jsonObject.address.city).isEqualTo("San Diego");
            assertThat(jsonObject.address.state).isEqualTo("CA");

        }
    }

    @Test
    void jsonParseTestByJackson() throws IOException {
        try (
                InputStream resource = cl.getResourceAsStream("example.json");
                InputStreamReader reader = new InputStreamReader(resource)
        ) {
            CustomerData jsonCustomerData = objectMapper.readValue(reader, CustomerData.class);
            assertThat(jsonCustomerData.firstName).isEqualTo("Joe");
            assertThat(jsonCustomerData.lastName).isEqualTo("Jackson");
            assertThat(jsonCustomerData.gender).isEqualTo("male");
            assertThat(jsonCustomerData.age).isEqualTo(28);
            assertThat(jsonCustomerData.address.streetAddress).isEqualTo(101);
            assertThat(jsonCustomerData.address.city).isEqualTo("San Diego");
            assertThat(jsonCustomerData.address.state).isEqualTo("CA");

        }
    }
}