package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

//    @Autowired
//    public BasicItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items",items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "/basic/item";
    }

    //같은 URL이지만 매핑방식이 다르므로 다르게 이동
    @GetMapping("/add")
    public String addForm(){
        return "/basic/addForm";
    }

//    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName, @RequestParam int price, @RequestParam Integer quantity, Model model){
        Item item = new Item(itemName, price, quantity); //set으로 넣어도 됨
        itemRepository.save(item);

        model.addAttribute("item",item);
        return "/basic/item";
    }

//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item){
//        Item item = new Item(itemName, price, quantity); // @ModelAttribute에서 객체 생성
        itemRepository.save(item);

//        model.addAttribute("item",item); //@ModelAttribute를 쓴다면, 자동추가가 되기 때문에 생략 가능 + model도 지워도 됨
        return "/basic/item";
    }

//    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item){
        // @ModelAttribute의 ("name") 네임을 생략한다면, Item의 첫번째 문자를 소문자로 바꿔서 자동생성

        itemRepository.save(item);
        return "/basic/item";
    }

//    @PostMapping("/add")
    public String addItemV4(Item item){
        // @ModelAttribute 생략 가능

        itemRepository.save(item);
        return "/basic/item";
    }

//    @PostMapping("/add")
    public String addItemV5(Item item){
        // @ModelAttribute 생략 가능

        itemRepository.save(item);
        return "redirect:/basic/items/"+item.getId(); //상세페이지 이동
    }

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes){
        // @ModelAttribute 생략 가능
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId",savedItem.getId());
        redirectAttributes.addAttribute("status",true);

        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);

        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String editSave(@PathVariable Long itemId, @ModelAttribute Item item){
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }



    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }


}
