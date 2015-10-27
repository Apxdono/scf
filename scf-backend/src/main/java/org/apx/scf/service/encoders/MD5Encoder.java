package org.apx.scf.service.encoders;

import org.apache.commons.beanutils.PropertyUtils;
import org.apx.scf.extra.IFieldEncoder;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Oleg on 27.10.2015.
 */
@Component(value = "md5Encoder")
public class MD5Encoder implements IFieldEncoder<String> {

    @Override
    public void encode(Object target, String fieldName, Object defaultValue) {
        try {
            PropertyDescriptor pd = PropertyUtils.getPropertyDescriptor(target, fieldName);
            String initialValue = (String) pd.getReadMethod().invoke(target);
            initialValue = initialValue != null ? initialValue : defaultValue + "";
            String encodedValue = new Md5PasswordEncoder().encodePassword(initialValue,null);
            pd.getWriteMethod().invoke(target,encodedValue);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
