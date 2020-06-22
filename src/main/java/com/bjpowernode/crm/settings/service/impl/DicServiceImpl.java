package com.bjpowernode.crm.settings.service.impl;

import com.bjpowernode.crm.settings.dao.DicTypeDao;
import com.bjpowernode.crm.settings.dao.DicValueDao;
import com.bjpowernode.crm.settings.domain.DicType;
import com.bjpowernode.crm.settings.domain.DicValue;
import com.bjpowernode.crm.settings.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * lzx
 * 2020/2/19
 */

@Service
public class DicServiceImpl implements DicService {
    @Autowired
    private DicTypeDao dicTypeDao;

    @Autowired
    private DicValueDao dicValueDao;

    @Override
    public List<DicType> getDicTypeList() {
        List<DicType> dicTypeList = dicTypeDao.getDicTypeList();

        return dicTypeList;
    }

    @Override
    public boolean checkCode(String code) {
        boolean flag = true;

        int count=dicTypeDao.checkCode(code);

        if(count !=0){
            //如此count不等于0，说明我们根据这个code，查询到了相应的信息
            //说明编码已经重复了
            flag=false;
        }

        return flag;
    }

    @Override
    public void saveType(DicType dt) {
        dicTypeDao.saveType(dt);
    }

    @Override
    public DicType getDicTypeByCode(String code) {

        DicType dt=dicTypeDao.getDicTypeByCode(code);

        return dt;
    }

    @Override
    public void UpdateType(DicType dt) {

        dicTypeDao.UpdateType(dt);

    }

    @Override
    public void deleteType(String[] codes) {

        dicValueDao.deleteValueByCodes(codes);
        dicTypeDao.deleteTypeByCodes(codes);
    }


    @Override
    public List<DicValue> getDicValueList() {

        List<DicValue> dvList=dicValueDao.getDicValueList();

        return dvList;
    }

    @Override
    public void saveValue(DicValue dv) {

        dicValueDao.saveValue(dv);
    }

    @Override
    public boolean codeByTypeCode(DicValue dv) {
        boolean flag=true;
        int count=dicValueDao.codeByTypeCode(dv);
        if(count!=0){
            flag=false;
        }
        return flag;
    }

    @Override
    public DicValue getDicValueById(String id) {
        DicValue dv=dicValueDao.getDicValueById(id);
        return dv;
    }

    @Override
    public void deleteValue(String[] id) {
        dicValueDao.deleteValue(id);
    }

    @Override
    public void updateValue(DicValue dv) {
        dicValueDao.updateValue(dv);
    }

    @Override
    public List<DicValue> queryDivValueByTyPeCode(String typeCode) {

        return dicValueDao.seleteDivValueByTyPeCode(typeCode);
    }


}
