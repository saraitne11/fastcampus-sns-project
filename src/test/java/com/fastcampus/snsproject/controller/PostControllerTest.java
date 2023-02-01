package com.fastcampus.snsproject.controller;

import com.fastcampus.snsproject.controller.request.PostCreateRequest;
import com.fastcampus.snsproject.controller.request.PostModifyRequest;
import com.fastcampus.snsproject.controller.request.UserJoinRequest;
import com.fastcampus.snsproject.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PostService postService;

    @Test
    @WithMockUser
    void 포스트작성() throws Exception {
        String title = "title";
        String body = "body";

        mockMvc.perform(post("/api/v1/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new PostCreateRequest(title, body)))
                ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void 포스트작성시_로그인_하지않은_경우() throws Exception {
        String title = "title";
        String body = "body";

        mockMvc.perform(post("/api/v1/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new PostCreateRequest(title, body)))
                ).andDo(print())
                .andExpect(status().isUnauthorized());
    }
    //
    // @Test
    // @WithMockUser
    // void 포스트수정() throws Exception {
    //     String title = "title";
    //     String body = "body";
    //
    //     mockMvc.perform(put("/api/v1/posts/1")
    //                     .contentType(MediaType.APPLICATION_JSON)
    //                     .content(objectMapper.writeValueAsBytes(new PostModifyRequest(title, body)))
    //             ).andDo(print())
    //             .andExpect(status().isOk());
    // }
    //
    // @Test
    // @WithAnonymousUser
    // void 포스트수정시_로그인_하지않은_경우() throws Exception {
    //     String title = "title";
    //     String body = "body";
    //
    //     mockMvc.perform(put("/api/v1/posts/1")
    //                     .contentType(MediaType.APPLICATION_JSON)
    //                     .content(objectMapper.writeValueAsBytes(new PostModifyRequest(title, body)))
    //             ).andDo(print())
    //             .andExpect(status().isUnauthorized());
    // }
    //
    // @Test
    // @WithMockUser
    // void 포스트수정시_본인이_작성한_글이_아닐_경우() throws Exception {
    //     String title = "title";
    //     String body = "body";
    //
    //     mockMvc.perform(put("/api/v1/posts/1")
    //                     .contentType(MediaType.APPLICATION_JSON)
    //                     .content(objectMapper.writeValueAsBytes(new PostModifyRequest(title, body)))
    //             ).andDo(print())
    //             .andExpect(status().isUnauthorized());
    // }

}
