package com.sonicge.sse;

public  interface StreamCallBack {
    //收到content增量内容
    void onToken(String token);
    //流正常结束 返回完整内容和token统计
    void onComplete(String fullContent, Usage usage);
    //发生错误 partialContent是错误发生已经收到的内容
    void onError(Exception e,String partialContent);
}
