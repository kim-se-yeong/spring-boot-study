package com.example.validation.controller;

import com.example.project.domain.Item;
import com.example.project.repository.ItemRepository;
import com.example.validation.ItemValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/validation")
public class ItemValidationController {

    //생성자 주입
    private final ItemRepository itemRepository;

    private final ItemValidator itemValidator;

    @Autowired
    public ItemValidationController(ItemRepository itemRepository, ItemValidator itemValidator) {
        this.itemRepository = itemRepository;
        this.itemValidator = itemValidator;
    }

    @InitBinder //해당 컨트롤러에만 영향을 준다.
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(itemValidator);
    }

    @GetMapping
    //view return
    public String getList(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "validation/items";
    }

    @GetMapping("{itemId}")
    public String get(@PathVariable("itemId") Long id, Model model) {
        Item item = itemRepository.findById(id);
        model.addAttribute("item", item);
        return "validation/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "validation/addForm";
    }

    /**
     * map 활용하여 에러 리턴
     * @param item
     * @param model
     * @return
     */
//    @PostMapping("/add")
    public String save(@ModelAttribute Item item, Model model) {

        Map<String, String> errors = new HashMap<>();
        //필드 오류 검증
        if (!StringUtils.hasText(item.getName())) {
            errors.put("itemName", "상품 이름은 필수입니다.");
        }
        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            errors.put("price", "가격은 1,000 ~ 1,000,000 까지 허용합니다.");
        }
        if (item.getQuantity() == null || item.getQuantity() >= 9999) {
            errors.put("quantity", "수량은 최대 9,999 까지 허용합니다.");
        }

        //특정 필드가 아닌 복합 룰 검증
        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                errors.put("globalError", "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + resultPrice);
            }
        }

        //검증에 실패하면 다시 입력 폼으로
        if (!errors.isEmpty()) {
            log.info("errors = {}", errors);
            model.addAttribute("errors", errors);
            return "validation/addForm";
        }
        itemRepository.save(item);
        return "validation/item";
    }

    /**
     * BindingResult 객체 활용하여 에러 리턴
     * @param item
     * @param bindingResult
     * @param model
     * @return
     */
//    @PostMapping("/add")
    public String save(@ModelAttribute Item item, BindingResult bindingResult, Model model) {

        //필드 오류 검증
        if (!StringUtils.hasText(item.getName())) {
            bindingResult.addError(new FieldError("item", "name", "상품 이름은 필수입니다."));
        }
        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            bindingResult.addError(new FieldError("item", "price", "가격은 1,000 ~ 1,000,000 까지 허용합니다."));
        }
        if (item.getQuantity() == null || item.getQuantity() >= 9999) {
            bindingResult.addError(new FieldError("item", "quantity", "수량은 최대 9,999 까지 허용합니다."));
        }

        //특정 필드가 아닌 복합 룰 검증
        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.addError(new ObjectError("item", "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + resultPrice));
            }
        }

        //검증에 실패하면 다시 입력 폼으로
        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "validation/addForm";
        }
        itemRepository.save(item);
        return "validation/item";
    }

    /**
     *
     * @param item
     * @param bindingResult
     * @param model
     * @return
     */
//    @PostMapping("/add")
    public String saveV2(@ModelAttribute Item item, BindingResult bindingResult, Model model) {

        log.info("** bindingResult = {}", bindingResult);
        //필드 오류 검증
        if (!StringUtils.hasText(item.getName())) {
            /**
             * objectName : 오류가 발생한 객체 이름
             * field : 오류 필드
             * rejectedName : 사용자가 입력한 값(거절된 값)
             * bindingFailure : 타입 오류같은 바인딩 실패인지, 검증 실패인지 구분 값
             * codes : 메시지 코드
             * arguments : 메시지에서 사용하는 인자
             * defaultMessage : 기본 오류 메시지
             * */
            bindingResult.addError(new FieldError("item", "name", item.getName(), false, null, null, "상품 이름은 필수입니다."));
        }
        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            bindingResult.addError(new FieldError("item", "price", item.getPrice(), false, null, null, "가격은 1,000 ~ 1,000,000 까지 허용합니다."));
        }
        if (item.getQuantity() == null || item.getQuantity() >= 9999) {
            bindingResult.addError(new FieldError("item", "quantity", item.getQuantity(), false, null, null, "수량은 최대 9,999 까지 허용합니다."));
        }

        //특정 필드가 아닌 복합 룰 검증
        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.addError(new ObjectError("item", null, null, "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + resultPrice));
            }
        }

        //검증에 실패하면 다시 입력 폼으로
        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "validation/addForm";
        }
        itemRepository.save(item);
        return "validation/item";
    }

    //properties 값 활용하기
//    @PostMapping("/add")
    public String saveV4(@ModelAttribute Item item, BindingResult bindingResult, Model model) {

        log.info("bindingResult = {}", bindingResult);
        log.info("bindingResult.Object = {}", bindingResult.getObjectName());
        log.info("target = {}", bindingResult.getTarget());
        //필드 오류 검증
        if (!StringUtils.hasText(item.getName())) {
            bindingResult.addError(new FieldError("item", "name", item.getName(), false, new String[]{"required.item.itemName"}, null, null));
        }
        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            bindingResult.addError(new FieldError("item", "price", item.getPrice(), false, new String[]{"range.item.price"}, new Object[]{1000, 1000000}, null));
        }
        if (item.getQuantity() == null || item.getQuantity() >= 9999) {
            bindingResult.addError(new FieldError("item", "quantity", item.getQuantity(), false, new String[]{"max.item.quantity"}, new Object[]{9999}, null));
        }

        //특정 필드가 아닌 복합 룰 검증
        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.addError(new ObjectError("item", new String[]{"totalPriceMin"}, new Object[]{10000, resultPrice}, null));
            }
        }

        //검증에 실패하면 다시 입력 폼으로
        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "validation/addForm";
        }
        itemRepository.save(item);
        return "validation/item";
    }

//    @PostMapping("/add")
    public String saveV5(@ModelAttribute Item item, BindingResult bindingResult, Model model) {

        log.info("bindingResult.Object = {}", bindingResult.getObjectName());
        log.info("target = {}", bindingResult.getTarget());

        //필드 오류 검증
        if (!StringUtils.hasText(item.getName())) {
//            bindingResult.addError(new FieldError("item", "name", item.getName(), false, new String[]{"required.item.itemName"}, null, null));
            bindingResult.rejectValue("name", "required");
        }
        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            bindingResult.rejectValue("price", "range", new Object[]{1000, 1000000}, null);
//            bindingResult.addError(new FieldError("item", "price", item.getPrice(), false, new String[]{"range.item.price"}, new Object[]{1000, 1000000}, null));
        }
        if (item.getQuantity() == null || item.getQuantity() >= 9999) {
            bindingResult.rejectValue("quantity", "max", new Object[]{9999}, null);
//            bindingResult.addError(new FieldError("item", "quantity", item.getQuantity(), false, new String[]{"max.item.quantity"}, new Object[]{9999}, null));
        }

        //특정 필드가 아닌 복합 룰 검증
        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{1000, resultPrice}, null);
//                bindingResult.addError(new ObjectError("item", new String[]{"totalPriceMin"}, new Object[]{10000, resultPrice}, null));
            }
        }

        //검증에 실패하면 다시 입력 폼으로
        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "validation/addForm";
        }

        itemRepository.save(item);
        return "validation/item";
    }

//    @PostMapping("/add")
    public String saveV6(@ModelAttribute Item item, BindingResult bindingResult, Model model) {

        log.info("bindingResult.Object = {}", bindingResult.getObjectName());
        log.info("target = {}", bindingResult.getTarget());

        //필드 오류 검증
        ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "name", "required");

        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            bindingResult.rejectValue("price", "range", new Object[]{1000, 1000000}, null);
//            bindingResult.addError(new FieldError("item", "price", item.getPrice(), false, new String[]{"range.item.price"}, new Object[]{1000, 1000000}, null));
        }
        if (item.getQuantity() == null || item.getQuantity() >= 9999) {
            bindingResult.rejectValue("quantity", "max", new Object[]{9999}, null);
//            bindingResult.addError(new FieldError("item", "quantity", item.getQuantity(), false, new String[]{"max.item.quantity"}, new Object[]{9999}, null));
        }

        //특정 필드가 아닌 복합 룰 검증
        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{1000, resultPrice}, null);
//                bindingResult.addError(new ObjectError("item", new String[]{"totalPriceMin"}, new Object[]{10000, resultPrice}, null));
            }
        }

        //검증에 실패하면 다시 입력 폼으로
        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "validation/addForm";
        }

        itemRepository.save(item);
        return "validation/item";
    }

    //검증 로직 분리
//    @PostMapping("/add")
    public String saveV7(@ModelAttribute Item item, BindingResult bindingResult, Model model) {

        itemValidator.validate(item, bindingResult);

        //검증에 실패하면 다시 입력 폼으로
        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "validation/addForm";
        }

        itemRepository.save(item);
        return "validation/item";
    }

    //검증기 사용
    @PostMapping("/add")
    public String saveV8(@Validated @ModelAttribute Item item, BindingResult bindingResult, Model model) {

        //검증에 실패하면 다시 입력 폼으로
        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "validation/addForm";
        }

        itemRepository.save(item);
        return "validation/item";
    }
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable("itemId") Long id, Model model) {
        Item item = itemRepository.findById(id);
        model.addAttribute("item", item);
        return "validation/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@ModelAttribute Item item) {
        itemRepository.updateById(item.getId(), item);
        return "redirect:/validation/items/{itemId}";
    }
}