package com.file.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDemo {
    private static final String baseUrl = "http://127.0.0.1:8001/fileserver";
//    @Autowired
//    private HttpRequest httpRequest;
//    @Autowired
//    RestTemplate restTemplate;
    private MockMvc mockMvc;

    @Test
    public void testApi01() throws IOException {
        //测试上传接口  @PostMapping("/uploadfile")
        String url = baseUrl+"/uploadfile";

//        BufferedInputStream bufferedInputStream = new BufferedInputStream(stream);
        //测试数据
        /**
         * "D:\电脑桌面文件\图片\Camera Roll\666.jpg"
         * "D:\\电脑桌面文件\\图片\\Camera Roll\\754.jpg"
         */
        String filepath = "D:\\电脑桌面文件\\图片\\Camera Roll\\754.jpg";
        File file = new File(filepath);

        FileInputStream fileInputStream = new FileInputStream(file);
        MockMultipartFile mockMultipartFile = new MockMultipartFile("754.jpg",file.getName(), String.valueOf(MediaType.ALL),fileInputStream);
        ResultActions resultActions = this.mockMvc.perform(multipart("/file/upload")
                .file(mockMultipartFile));
        resultActions.andExpect(status().isOk())
                .andReturn().getResponse()
                .setCharacterEncoding(StandardCharsets.UTF_8.name());
        resultActions.andDo(print());
    }
}
