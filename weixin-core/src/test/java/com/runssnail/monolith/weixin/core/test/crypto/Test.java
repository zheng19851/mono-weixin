package com.runssnail.monolith.weixin.core.test.crypto;


public class Test {

    public static void main(String[] args) throws AesException {
        WXBizMsgCrypt crypt = new WXBizMsgCrypt("hfkf2920fa729202jfjajf62", "gDAfxVckvJxbQzBuR0ILI4jB1bcDOnwGdWw0ogX97YK", "wx999ac9c317bdc6e4");
        
        String postData = "<xml><ToUserName><![CDATA[gh_467e5e8d88e0]]></ToUserName><Encrypt><![CDATA[zs3q5bNdoPsHnodVdgwFkyk+4nmxJfUn8ZdgLuqhRoAzH3JBxvEhVPBCo2Xzv9dA0VLj77eoz9U4C7afWRFYAB6nzFxfwedw9HdG2of4xRY9gpXZh0I2QWtEXLTfYWTr7xrUKjByExEAR7FZ5eAQpv8vuBjuvLR1HcAbX7jup0BqCp9okV3twLlYL/nT1jUD+qbFUbaq0j/43twU6TgNSZaO2nk+2LH33+RjYNZz1vuBAPSWQc2/ftbY3jMxFgqbqaz0Qu0nM2BnVZ15PXDB+TyDzssCbr/GhqBwWFI3f9ewpD2KqSaoBM7PbI3P3aGdkNl5hIgTqDL0wiVhu1dAHyhyIO1LzIPwc3YEUdz36DvafdGxoDuJ0uLhHjVD7eDV0MmWeMyCglwV3hmGgCRqIjYlMq+Pq7qA4ItVV8s9nvpsb/1FNlfRLJc7V0mEyWBUNvGxCiVz+J4WuJTYerCK0gE4uYl1aqqM2CrdKsyKcOqbsFy5xXJ1bFqm/TPHI29d]]></Encrypt></xml>";
        String result = crypt.decryptMsg("22bfdaff81a961d3f315c0cff5559816e11972cb", "1414727763", "888885134", postData);
        System.out.println(result);

    }

}
