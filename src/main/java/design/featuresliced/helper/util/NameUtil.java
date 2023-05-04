package design.featuresliced.helper.util;

import com.intellij.openapi.util.text.StringUtil;

import java.util.Arrays;
import java.util.stream.Collectors;

public class NameUtil {

    public static String normalizeNameForUiIfNeed(String name) {
        return Arrays.stream(name.split("[-_]")).map(StringUtil::capitalize).collect(Collectors.joining(""));
    }

}
