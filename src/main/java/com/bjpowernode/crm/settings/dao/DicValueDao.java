package com.bjpowernode.crm.settings.dao;

import com.bjpowernode.crm.settings.domain.DicValue;

import java.util.List;

/**
 * lzx
 * 2020/2/18
 */
public interface DicValueDao {

    void deleteValueByCodes(String[] codes);

    List<DicValue> getDicValueList();

    void saveValue(DicValue dv);

    int codeByTypeCode(DicValue dv);

    DicValue getDicValueById(String id);

    void deleteValue(String[] id);

    void updateValue(DicValue dv);

    List<DicValue> seleteDivValueByTyPeCode(String typeCode);
}
