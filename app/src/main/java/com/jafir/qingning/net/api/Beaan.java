package com.jafir.qingning.net.api;

/**
 * Created by jafir on 16/6/2.
 */
public class Beaan {

    /**
     * message : Successful
     * rcode : 1000
     * data : {"user":{"id":7,"lastUpdateTime":1464838224000,"createTime":1464838224000,"nickName":"hanjianf","phone":"13982001234","password":null,"sex":null,"headImgUrl":"http://img1.lukou.com/static/p/avatar/large/0000/02/16/85/21685-2.jpg","description":null}}
     * rescode : PASS_OK
     */

    private String message;
    private int rcode;
    /**
     * user : {"id":7,"lastUpdateTime":1464838224000,"createTime":1464838224000,"nickName":"hanjianf","phone":"13982001234","password":null,"sex":null,"headImgUrl":"http://img1.lukou.com/static/p/avatar/large/0000/02/16/85/21685-2.jpg","description":null}
     */

    private DataBean data;
    private String rescode;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRcode() {
        return rcode;
    }

    public void setRcode(int rcode) {
        this.rcode = rcode;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getRescode() {
        return rescode;
    }

    public void setRescode(String rescode) {
        this.rescode = rescode;
    }

    public static class DataBean {
        /**
         * id : 7
         * lastUpdateTime : 1464838224000
         * createTime : 1464838224000
         * nickName : hanjianf
         * phone : 13982001234
         * password : null
         * sex : null
         * headImgUrl : http://img1.lukou.com/static/p/avatar/large/0000/02/16/85/21685-2.jpg
         * description : null
         */

        private UserBean user;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            private int id;
            private long lastUpdateTime;
            private long createTime;
            private String nickName;
            private String phone;
            private Object password;
            private Object sex;
            private String headImgUrl;
            private Object description;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public long getLastUpdateTime() {
                return lastUpdateTime;
            }

            public void setLastUpdateTime(long lastUpdateTime) {
                this.lastUpdateTime = lastUpdateTime;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public Object getPassword() {
                return password;
            }

            public void setPassword(Object password) {
                this.password = password;
            }

            public Object getSex() {
                return sex;
            }

            public void setSex(Object sex) {
                this.sex = sex;
            }

            public String getHeadImgUrl() {
                return headImgUrl;
            }

            public void setHeadImgUrl(String headImgUrl) {
                this.headImgUrl = headImgUrl;
            }

            public Object getDescription() {
                return description;
            }

            public void setDescription(Object description) {
                this.description = description;
            }
        }
    }
}
