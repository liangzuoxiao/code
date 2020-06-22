package com.bjpowernode.crm.settings.dao;

import com.bjpowernode.crm.settings.domain.DicType;

import java.util.List;

/**
 * lzx
 * 2020/2/18
 */
public interface DicTypeDao {

    List<DicType> getDicTypeList();

    int checkCode(String code);

    void saveType(DicType dt);

    DicType getDicTypeByCode(String code);

    void UpdateType(DicType dt);

    void deleteTypeByCodes(String[] codes);
}
