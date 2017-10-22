package com.matt.festec.generators;

import com.matt.latter.annotations.AppRegisterGenerator;
import com.matt.latter.wechat.templates.AppRegisterTemplate;

/**
 * Created by mamen on 2017/10/14.
 */
@AppRegisterGenerator(
        packageName = "com.matt.festec",
        registerTemplete = AppRegisterTemplate.class
)
public interface AppRegister {
}
