package com.cinema.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cinema.web.common.common.Criteria;
import com.cinema.web.common.common.PageMaker;
import com.cinema.web.service.BoardService;

@Controller
public class BoardController {	
	
	@Resource(name="boardService")
	private BoardService boardService;
	
	@RequestMapping(value="/board/list")
	public ModelAndView openBoardList(Criteria cri) throws Exception {
		
		ModelAndView mv = new ModelAndView("/board/boardList");
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(boardService.countBoardListTotal()); //총 게시글 수
		
		List<Map<String, Object>> list = boardService.selectBoardList(cri);
		mv.addObject("list", list);
		mv.addObject("pageMaker", pageMaker);
		
		return mv;
		
	}
	
	@RequestMapping(value="/board/write")
	public ModelAndView boardWrite() throws Exception {
		
		ModelAndView mv = new ModelAndView("/board/boardWrite");
		
		return mv;
	}

	@RequestMapping(value="/board/write", method=RequestMethod.POST)
	public ModelAndView boardInsert(BoardForm boardForm) throws Exception {
		
		ModelAndView mv = new ModelAndView("redirect:/board/list");
		Map<String, Object> items = new HashMap<>();
		items.put("title", boardForm.getTitle());
		items.put("contents", boardForm.getContents());
		items.put("crea_id", boardForm.getCrea_Id());

		boardService.insertBoard(items);
		
		return mv;
	}

	@GetMapping("/board/detail/{idx}")
	public ModelAndView boardDetail(@PathVariable int idx, Criteria cri) {
		ModelAndView mv = new ModelAndView("/board/boardDetail");
		Map<String, Object> detail = boardService.viewBoardDetail(idx);
		mv.addObject("detail", detail);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		mv.addObject("page", cri.getPage());
		mv.addObject("pageMaker", pageMaker);
		
		return mv;
	}
	
	@RequestMapping(value="/board/update/{idx}")
	public ModelAndView boardUpdate(@PathVariable int idx, Criteria cri) throws Exception {
		
		ModelAndView mv = new ModelAndView("board/boardUpdate");
		Map<String, Object> detail = boardService.selectBoardDetail(idx);
		mv.addObject("detail", detail);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		mv.addObject("page", cri.getPage());
		mv.addObject("pageMaker", pageMaker);

		mv.addObject("boardForm", new BoardForm());
		
		return mv;
	}	

	@RequestMapping(value="/board/update/{idx}", method=RequestMethod.POST)
	public ModelAndView boardUpdatePOST(@PathVariable int idx, Criteria cri, RedirectAttributes redAttr, BoardForm boardForm) throws Exception {
		
		ModelAndView mv = new ModelAndView("redirect:/board/list");

		Map<String, Object> items = new HashMap<>();
		items.put("idx", idx);
		items.put("title", boardForm.getTitle());
		items.put("contents", boardForm.getContents());

		boardService.updateBoard(items);
		
		return mv;
	}
	
	@RequestMapping(value="/board/delete/{idx}")
	public ModelAndView boardDelete(@PathVariable int idx, Criteria cri, RedirectAttributes redAttr) throws Exception {
		
		ModelAndView mv = new ModelAndView("redirect:/board/list");
		boardService.deleteBoard(idx);
		
		redAttr.addAttribute("page", cri.getPage());
		redAttr.addAttribute("perPageNm", cri.getPerPageNum());
		
		return mv;
	}
}
