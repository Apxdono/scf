package org.apx.scf.extra;

/**
 * Created by Oleg on 27.10.2015.
 */
public interface IFieldEncoder<FType> {
    void encode(Object target,String fieldName,Object defaultValue);
}
