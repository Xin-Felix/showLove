package work.huangxin.collect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import work.huangxin.collect.model.Message;
import work.huangxin.collect.service.MessageService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class CollectController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/")
    public String getIndex(HttpServletRequest request, Model model) {
        String ip = returnIp(request);
        model.addAttribute("message", getMessage(ip));
        return "index";
    }

    @GetMapping("/{id}")
    public String showLove(Model model, @PathVariable Integer id, HttpServletRequest request) {
        Message byId = messageService.getById(id);
        if (byId == null) {
            String ip = returnIp(request);
            model.addAttribute("message", getMessage(ip));
            return "index";
        }
        model.addAttribute("love", byId);
        return "show";
    }

    public List<Message> getMessage(String ip) {
        Message message = new Message();
        message.setIp(ip);
        List<Message> list = messageService.findList(message);
        return list;
    }


    @PostMapping("/add/addMessage")
    @Transactional
    @ResponseBody
    public Integer addMessage(@RequestBody Message message, HttpServletRequest request) {

        String ip = returnIp(request);
        Message messages = new Message();
        message.setIp(ip);
        List<Message> list = messageService.findList(messages);
        if (list.size() >= 3) {
            return 5000;
        }
        message.setIp(ip);
        messageService.add(message);
        Message user = new Message();
        user.setContent(message.getContent());
        user.setMeName(message.getMeName());
        return messageService.findList(user).get(0).getId();
    }

    /**
     * 获取用户ip
     *
     * @param request
     * @return
     */
    public String returnIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}