package com.matt.festec.generators;

import com.matt.latter.annotations.PayEntryGenerator;
import com.matt.latter.wechat.templates.WXPayEntryTemplate;

/**
 * Created by mamen on 2017/10/14.
 */
@PayEntryGenerator(
        packageName = "com.matt.festec",
        payEntryTemplete = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
