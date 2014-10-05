package com.runssnail.monolith.weixin.client.user;

import com.runssnail.monolith.common.DomainBase;

/**
 * ΢���û���Ϣ
 * 
 * @author zhengwei
 */
public class User extends DomainBase {

    /**
     * 
     */
    private static final long serialVersionUID = 422465530058943744L;

    // openid �û���Ψһ��ʶ
    private String            openid;

    // nickname �û��ǳ�
    private String            nickName;

    // sex �û����Ա�ֵΪ1ʱ�����ԣ�ֵΪ2ʱ��Ů�ԣ�ֵΪ0ʱ��δ֪
    private String            sex;

    // province �û�����������д��ʡ��
    private String            province;

    // city ��ͨ�û�����������д�ĳ���
    private String            city;

    // country ���ң����й�ΪCN
    private String            country;

    // headimgurl �û�ͷ�����һ����ֵ����������ͷ���С����0��46��64��96��132��ֵ��ѡ��0����640*640������ͷ�񣩣��û�û��ͷ��ʱ����Ϊ��
    private String            headimgurl;

    // privilege �û���Ȩ��Ϣ��json ���飬��΢���ֿ��û�Ϊ��chinaunicom��
    private String            privilege;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

}
