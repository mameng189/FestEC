package com.matt.festec.generators;
import com.matt.latter.annotations.EntryGenerator;
import com.matt.latter.wechat.templates.WXEntryTemplate;

/**
 * Created by mamen on 2017/10/14.
 */
@EntryGenerator(
        packageName = "com.matt.festec",
        entryTemplete = WXEntryTemplate.class
)
public interface WeChatEntry {
}
