package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.InquiryForm;
import com.example.demo.models.InquiryForm2;
import com.example.demo.models.InquiryForm3;
import com.example.demo.models.ItemForm;
import com.example.demo.repositries.InquiryRepository;
import com.example.demo.repositries.InquiryRepository2;
import com.example.demo.repositries.InquiryRepository3;
import com.example.demo.repositries.ItemRepository;

import java.util.List;


@Controller
@RequestMapping("/")
public class RootController {

	@Autowired
	InquiryRepository repository;

	@Autowired
	InquiryRepository2 repository2;

	@Autowired
	InquiryRepository3 repository3;

	@Autowired
	ItemRepository itemRepository;



	@GetMapping
	public String index() {
		return "root/index";
	}

	/**
	 * お問合せ1
	 * @param inquiryForm
	 * @return
	 */
	@GetMapping("/form")
	public String form(InquiryForm inquiryForm) {
		return "root/form";
	}

	/**
	 * お問合せ1
	 * @param inquiryForm
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	@PostMapping("/form")
	public String form(@Validated InquiryForm inquiryForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "root/form";
		}


		repository.saveAndFlush(inquiryForm);
		inquiryForm.clear();
		model.addAttribute("message", "お問い合わせを受け付けました。");
		return "root/form";
	}


	/**
	 * お問合せ２
	 * @param inquiryForm
	 * @return
	 */
	@GetMapping("/form2")
	public String form2(InquiryForm2 inquiryForm) {
		return "root/form2";
	}

	/**
	 * お問合せ２
	 * @param inquiryForm
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	@PostMapping("/form2")
	public String form2(@Validated InquiryForm2 inquiryForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "root/form2";
		}


		repository2.saveAndFlush(inquiryForm);
		inquiryForm.clear();
		model.addAttribute("message", "お問い合わせを受け付けました。");
		return "root/form2";
	}

	/**
	 * お問い合わせ３
	 * @param inquiryForm3
	 * @return
	 */
	@GetMapping("/form3")
	public String form3(InquiryForm3 inquiryForm) {
		return "root/form3";
	}

	/**
	 * お問い合わせ３
	 * @param inquiryForm3
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	@PostMapping("/form3")
	public String form3(@Validated InquiryForm3 inquiryForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "root/form3";
		}

		repository3.saveAndFlush(inquiryForm);
		inquiryForm.clear();
		model.addAttribute("message", "お問い合わせを受け付けました。");
		return "root/form3";
	}

	/**
	 * 新規登録画面
	 * @param itemForm
	 * @return
	 */
	@GetMapping("/create")
	public String create(ItemForm itemForm) {
		return "root/item/create";
	}

	/**
	 * 新規登録画面
	 * @param itemForm
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	@PostMapping("/create")
	public String create(@Validated ItemForm itemForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "root/item/create";
		}

		itemRepository.saveAndFlush(itemForm);
		itemForm.clear();
		model.addAttribute("message", "商品を新規に登録しました");
		return "root/item/create";
	}

	/**
	 * 商品一覧画面
	 * @param model
	 * @return
	 */
	@GetMapping("/list")
	public String list(Model model) { 
		showList(model);
		return "root/item/list"; 
	}
	
	/**
	 * 商品一覧画面（削除ボタン）
	 * @param id
	 * @param model
	 * @return
	 */
	@PostMapping("/{id}/delete")
    public String delete(@PathVariable String id, Model model) {
        itemRepository.deleteById(id);
		showList(model);
        return "root/item/list"; 
    }

	/**
	 * 編集画面
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable String id, Model model) {
		ItemForm item = itemRepository.findById(id).get();
		model.addAttribute("itemForm", item);
		return "root/item/edit";
	}

	/**
	 * 編集画面（更新ボタン）
	 * @param id
	 * @param item
	 * @param model
	 * @return
	 */
	@PostMapping("/{id}/edit")
	public String update(@PathVariable String id, @ModelAttribute ItemForm item, Model model) {
		item.setId(id);
		itemRepository.saveAndFlush(item);
		showList(model);
		return "root/item/list"; 
	}
	

	public void showList(Model model) {
		List<ItemForm> itemList = itemRepository.findAll();
		model.addAttribute("itemList", itemList); 
	}
	
	
}

