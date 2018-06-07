//import com.demo.controller.Home;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.core.io.Resource;
//import org.springframework.http.*;
//import org.springframework.mock.web.MockServletContext;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;

//import java.nio.file.Files;
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = MockServletContext.class)
//@WebAppConfiguration
public class TestWeb {

//    private MockMvc mvc;
//
//    @Before
//    public void setUp() throws Exception {
//        mvc = MockMvcBuilders.standaloneSetup(new Home()).build();
//    }
//
//    @Test
//    public void getHello() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().string(equalTo("Hello World!!!")));
//
//        Map<String,String> multiValueMap = new HashMap<>();
//        multiValueMap.put("username","lake");//传值，但要在url上配置相应的参数
//        ActResult result = testRestTemplate.getForObject("/",String.class,multiValueMap);
//        Assert.assertEquals(result.getCode(),0);
//    }
//
//    @Test
//    public void post1() throws Exception {
//        mvc.perform(post("/")
//                .contentType(MediaType.APPLICATION_JSON).content(requestBody)
//                .accept(MediaType.APPLICATION_JSON)) //执行请求
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) //验证响应contentType
//                .andExpect(jsonPath("$.id").value(1)); //使用Json path验证JSON 请参考http://goessner.net/articles/JsonPath/
//    }
//
//    @Test
//    public void upload() throws Exception {
//        Resource resource = new FileSystemResource("/home/lake/github/wopi/build.gradle");
//        MultiValueMap multiValueMap = new LinkedMultiValueMap();
//        multiValueMap.add("username","lake");
//        multiValueMap.add("files",resource);
//        ActResult result = testRestTemplate.postForObject("/test/upload",multiValueMap,ActResult.class);
//        Assert.assertEquals(result.getCode(),0);
//    }
//
//    @Test
//    public void download() throws Exception {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("token","xxxxxx");
//        HttpEntity formEntity = new HttpEntity(headers);
//        String[] urlVariables = new String[]{"admin"};
//        ResponseEntity<byte[]> response = testRestTemplate.exchange("/test/download?username={1}", HttpMethod.GET,formEntity,byte[].class,urlVariables);
//        if (response.getStatusCode() == HttpStatus.OK) {
//            Files.write(response.getBody(),new File("/home/lake/github/file/test.gradle"));
//        }
//    }
//
//    @Test
//    public void getHeader() throws Exception {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("token","xxxxxx");
//        HttpEntity formEntity = new HttpEntity(headers);
//        String[] urlVariables = new String[]{"admin"};
//        ResponseEntity<ActResult> result = testRestTemplate.exchange("/test/getHeader?username={username}", HttpMethod.GET,formEntity,ActResult.class,urlVariables);
//        Assert.assertEquals(result.getBody().getCode(),0);
//    }
//
//    @Test
//    public void putHeader() throws Exception {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("token","xxxxxx");
//        MultiValueMap multiValueMap = new LinkedMultiValueMap();
//        multiValueMap.add("username","lake");
//        HttpEntity formEntity = new HttpEntity(multiValueMap,headers);
//        ResponseEntity<ActResult> result = testRestTemplate.exchange("/test/putHeader", HttpMethod.PUT,formEntity,ActResult.class);
//        Assert.assertEquals(result.getBody().getCode(),0);
//    }
//    DELETE
//
//    @Test
//    public void delete() throws Exception {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("token","xxxxx");
//        MultiValueMap multiValueMap = new LinkedMultiValueMap();
//        multiValueMap.add("username","lake");
//        HttpEntity formEntity = new HttpEntity(multiValueMap,headers);
//        String[] urlVariables = new String[]{"admin"};
//        ResponseEntity<ActResult> result = testRestTemplate.exchange("/test/delete?username={username}", HttpMethod.DELETE,formEntity,ActResult.class,urlVariables);
//        Assert.assertEquals(result.getBody().getCode(),0);
//    }

}
