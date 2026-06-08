package com.min.edu.api;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

//Test를 위해서 무작위 포트의 사용을 위한 설정
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//MVC 테스트를 위한 자동 설정
@AutoConfigureMockMvc
class BookmarkControllerTest {

	//Test를 위한 MockMvc 객체를 선언
	@Autowired
	private MockMvc mvc;
	
	@Test
	void shouldBookmakrs() throws Exception {
		//1번 테스트 단순위 /api/bookmakrs의 호출 여부
		mvc.perform(MockMvcRequestBuilders.get("/api/bookmarks"))
			.andExpect(status().isOk());
		
		//jdbc:tc:postgresql:9.6.8:///databasename
		//테스트 컨테이너 추가
		@TestPropertySource(properties = {
				"spring.datasource.url=jdbc:tc:postgresql:14-alpine:///demo"
		

    .andExpect(jsonPath("$.totalElements", CoreMatchers.equalTo(15)))
			.andExpect(jsonPath("$.totalPages", CoreMatchers.equalTo(2)))
			.andExpect(jsonPath("$.currentPage", CoreMatchers.equalTo(1)))
			.andExpect(jsonPath("$.isFirst", CoreMatchers.equalTo(true)))
			.andExpect(jsonPath("$.isLast", CoreMatchers.equalTo(false)))
			.andExpect(jsonPath("$.hasNext", CoreMatchers.equalTo(true)))
			.andExpect(jsonPath("$.hasPrevious", CoreMatchers.equalTo(false)))
			
			
			})
 // 페이징을 값을 입력해서 나오는결과를 테스트 한다
 	// page가 1이 전달됐을때 테스트, page가 2가 전달됐을때 테스트
 	@ParameterizedTest
 	@CsvSource({"1,15,2,1,true,false,true,false", "2,15,2,2,false,true,false,true"})
 	void shouldBookmarksPage(
 			int pageNo, int totalElements, int totalPages, int currentPage,
 			boolean isFirst, boolean isLast, boolean hasNext, boolean hasPrevious
 			) throws Exception {
 		
 		mvc.perform(MockMvcRequestBuilders.get("/api/bookmarks?page="+pageNo))
 			.andExpect(status().isOk())
 			.andExpect(jsonPath("$.totalElements", CoreMatchers.equalTo(totalElements)))
 			.andExpect(jsonPath("$.totalPages", CoreMatchers.equalTo(totalPages)))
 			.andExpect(jsonPath("$.currentPage", CoreMatchers.equalTo(currentPage)))
 			.andExpect(jsonPath("$.isFirst", CoreMatchers.equalTo(isFirst)))
 			.andExpect(jsonPath("$.isLast", CoreMatchers.equalTo(isLast)))
 			.andExpect(jsonPath("$.hasNext", CoreMatchers.equalTo(hasNext)))
 			.andExpect(jsonPath("$.hasPrevious", CoreMatchers.equalTo(hasPrevious)))
 			;
 	}
}
