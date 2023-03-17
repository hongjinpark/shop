package com.example.hong.controller;


import com.example.hong.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final RoomService roomService;


    @GetMapping("/chat")
    public ModelAndView chat() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("chat");
        return mv;
    }


    //방 페이지
    @GetMapping("/room")
    public ModelAndView room() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("room");
        return mv;
    }

    //방 생성하기
   /* @PostMapping("/room")
    public List<Room> createRoom(@RequestBody Map<Object, Object> params) {
        List<Room> result = roomService.createRoom(params);

        return result;
    }*/

}
