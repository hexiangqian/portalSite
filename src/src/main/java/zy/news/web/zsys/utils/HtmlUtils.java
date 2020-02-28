package zy.news.web.zsys.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import java.util.List;

/**
 * html提取助手
 *
 * @author maoko
 * @date 2020/2/28 15:13
 */
public class HtmlUtils {
    /**
     * 提取文本
     *
     * @param node
     * @return
     */
    private static String extractText(Node node) {
        /* TextNode直接返回结果 */
        if (node instanceof TextNode) {
            return ((TextNode) node).text();
        }
        /* 非TextNode的Node，遍历其孩子Node */
        List<Node> children = node.childNodes();
        StringBuffer buffer = new StringBuffer();
        for (Node child : children) {
            buffer.append(extractText(child));
        }
        return buffer.toString();
    }

    /**
     * 使用jsoup解析html并转化为提取字符串
     *
     * @param html
     * @return
     */
    public static String html2Str(String html) {
        Document doc = Jsoup.parse(html);
        return extractText(doc);
    }

    /**
     * 提取指定长度html内容
     *
     * @param html
     * @param len
     * @return
     */
    public static String html2Str(String html, int len) {
        String tmpResult = html2Str(html);
        return tmpResult.substring(0, len).trim();
    }
}
