import React, {Component} from 'react';
import { Tabs } from 'antd';

import AdminSetting from './AdminSetting'

import {logintype} from './PageLogin'

const TabPane = Tabs.TabPane;



class PageAdmin extends Component{
    render(){
        if (logintype!='admin'){
            return (
                <h1 style={{fontSize:40}}>请用管理员账户登录</h1>
            )
        }
        return (
            <div className="card-container">
                <Tabs type="card">
                <TabPane tab="用户管理" key="3">
                    <AdminSetting/>
                </TabPane>
                </Tabs>
            </div>
        )
    }
}

export default PageAdmin;