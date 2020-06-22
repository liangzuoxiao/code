package com.bjpowernode.crm.settings.web.controller;


import com.bjpowernode.crm.settings.domain.DicType;
import com.bjpowernode.crm.settings.domain.DicValue;
import com.bjpowernode.crm.settings.service.DicService;
import com.bjpowernode.crm.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * lzx
 * 2020/2/18
 */
@Controller
@RequestMapping("/settings/dictionary")
public class DicController {
    @Autowired
    private DicService dicService;

    @RequestMapping("/toindex.do")
    public String toIndex(){

        return "/settings/dictionary/index";
    }

    @RequestMapping("/type/toindex.do")
    public ModelAndView typeToIndex(){

        List<DicType> dtList = dicService.getDicTypeList();

        ModelAndView mav =new ModelAndView();
        mav.addObject("dtList", dtList);
        mav.setViewName("/settings/dictionary/type/index");
        return mav;
    }

    @RequestMapping("/value/toindex.do")
    public ModelAndView valueToIndex(){
        List<DicValue> dvList= dicService.getDicValueList();
        ModelAndView mav= new ModelAndView();
        mav.addObject("dvList", dvList);
        mav.setViewName("/settings/dictionary/value/index");

        return mav;
    }

    @RequestMapping("/type/tosave.do")
    public String toTypesave(){

        return "/settings/dictionary/type/save";
    }

    @RequestMapping("/type/checkCode.do")
    @ResponseBody
    public Map<String,Object> checkCode(String code){

        boolean flag =dicService.checkCode(code);

        Map<String,Object> map = new HashMap<>();
        map.put("success", flag);
        return map;
    }

    @RequestMapping("/type/saveType.do")
    public String saveType(DicType dt){

        dicService.saveType(dt);

        return "redirect:/settings/dictionary/type/toindex.do";
    }

    @RequestMapping("/type/toTypeUpdate.do")
    public ModelAndView toTypeUpdate(String code){

        DicType dt=dicService.getDicTypeByCode(code);

        ModelAndView mav= new ModelAndView();
        mav.addObject("dt", dt);
        mav.setViewName("/settings/dictionary/type/edit");

        return mav;
    }

    @RequestMapping("/type/UpdateType.do")
    public String UpdateType(DicType dt){

        dicService.UpdateType(dt);

        return "redirect:/settings/dictionary/type/toindex.do";
    }

    @RequestMapping("/type/deleteType.do")
    public String deleteType(String[] codes){
        dicService.deleteType(codes);
        return "redirect:/settings/dictionary/type/toindex.do";
    }

    @RequestMapping("/value/tosave.do")
    public ModelAndView toValueSave(){
        List<DicType> dtList= dicService.getDicTypeList();
        ModelAndView mav = new ModelAndView();
        mav.addObject("dtList", dtList);
        mav.setViewName("/settings/dictionary/value/save");
        return mav;
    }

    @RequestMapping("/value/saveValue.do")
    public String saveValue(DicValue dv){
       String id= UUIDUtil.getUUID();
        dv.setId(id);
        dicService.saveValue(dv);

        return "redirect:/settings/dictionary/value/toindex.do";
    }

    @RequestMapping("/value/codeByTypeCode.do")
    @ResponseBody
    public Map<String,Object> codeByTypeCode(DicValue dv){

        boolean flag=dicService.codeByTypeCode(dv);
        Map<String,Object> map= new HashMap<>();
        map.put("success", flag);
        return map;
    }

    @RequestMapping("/value/toValueUpdate.do")
    public ModelAndView toValueUpdate(String id){
        DicValue dv=dicService.getDicValueById(id);
        ModelAndView mav=new ModelAndView();
        mav.addObject("dv", dv);
        mav.setViewName("/settings/dictionary/value/edit");
        return mav;
    }

    @RequestMapping("/value/deleteValue.do")
    public String deleteValue(String[] id){

        dicService.deleteValue(id);

        return  "redirect:/settings/dictionary/value/toindex.do";
    }

    @RequestMapping("/value/updateValue.do")
    public String updateValue(DicValue dv){

        dicService.updateValue(dv);

        return  "redirect:/settings/dictionary/value/toindex.do";
    }
}
