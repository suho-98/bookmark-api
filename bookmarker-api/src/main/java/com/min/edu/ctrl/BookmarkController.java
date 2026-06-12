package com.min.edu.ctrl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.min.edu.dto.BookmarkDto;
import com.min.edu.dto.BookmarksDto;
import com.min.edu.dto.CreateBookmarkRequest;
import com.min.edu.service.BookmarkService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

	private final BookmarkService bookmarkService;
	
	@GetMapping
	public BookmarksDto getBookmark(@RequestParam(name = "page", defaultValue = "1") Integer page,
								    @RequestParam(name = "query", defaultValue = "") String query){
		if(query == null || query.trim().length()==0) {
			return bookmarkService.getBookmarks(page);// 전체요청
		}
		
		return bookmarkService.seachBookmarks(query, page);
	}
	
	
	/*
	 * 해당 요청의 메소드가 성공 했을 경우 HttpStatus.CREATED 값인 201을 반환하도록 만듬
	 * 입력 받은 요청값을 @Valid를 통해서 처리되어 CreateBookmarkRequest 객체가 유효값을 처리 할 수 있도록 한다
	 */
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public BookmarkDto createBookmark(@RequestBody @Valid CreateBookmarkRequest request) {
		return bookmarkService.createBookmark(request);
	}
	
	@PutMapping("/{id}")
	public BookmarkDto updateBookmark(
	        @PathVariable Long id,
	        @RequestBody @Valid CreateBookmarkRequest request) {

	    return bookmarkService.updateBookmark(id, request);
	}

	@PatchMapping("/{id}")
	public BookmarkDto patchBookmark(
	        @PathVariable Long id,
	        @RequestBody CreateBookmarkRequest request) {

	    return bookmarkService.patchBookmark(id, request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBookmark(@PathVariable Long id) {

	    bookmarkService.deleteBookmark(id);
	}
	
}








