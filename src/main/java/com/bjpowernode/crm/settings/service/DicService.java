package com.bjpowernode.crm.settings.service;


import com.bjpowernode.crm.settings.domain.DicType;
import com.bjpowernode.crm.settings.domain.DicValue;

import java.util.List;

/**
 * lzx
 * 2020/2/19
 */
public interface DicService {
    List<DicType> getDicTypeList();

    boolean checkCode(String code);

    void saveType(DicType dt);

    DicType getDicTypeByCode(String code);

    void UpdateType(DicType dt);

    void deleteType(String[] codes);

    List<DicValue> getDicValueList();


    void saveValue(DicValue dv);

    boolean codeByTypeCode(DicValue dv);

    DicValue getDicValueById(String id);

    void deleteValue(String[] id);

    void updateValue(DicValue dv);

    List<DicValue> queryDivValueByTyPeCode(String typeCode);

}
