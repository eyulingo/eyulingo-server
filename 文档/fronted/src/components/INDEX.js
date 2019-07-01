import React, {Component} from 'react';
import { Switch, Route, Link } from 'react-router-dom'
import './tab.css'
import 'semantic-ui-css/semantic.min.css';
import PageLogin from './pages/PageLogin'
import RegisterForm from './pages/PageRegister'
import PageAdmin from './pages/PageAdmin'
import {logintype} from './pages/PageLogin'


class Index extends Component{
    logout(){
        // alert(document.cookie)
        document.cookie='username=;'
        window.location.href='/login'
    }

    render(){
        return (
            <div class="tab">
                <div class="header-m">
                <div class="nav-menu">
                    <div class="nav-mask">
                    </div>
                    <div class="bili-wrapper clearfix">
                    <div class="nav-con fl">
                        <ul class="rowDirection">
                        <li class="nav-item">
                            <Link tag="div" class="tab-item" to="/adminbooks" >
                            <span class="tab-link t">管理用户</span>
                            </Link>
                        </li>
                    <li>
                        <div class="reg">
                        <li class="nav-item">
                            <span class="tab-link">{logintype}</span>
                        </li>
                        </div>
                    </li>
                    <li>
                        <div class="info">
                        <li class="nav-item">
                            <div tag="div" class="tab-item">
                            <span class="tab-link t" onClick={this.logout} href='/login'>注销</span>
                            </div>
                        </li>
                        </div>
                    </li>
                    </ul>
                </div>

                    </div>
                </div>

                <div id="banner_link" class="head-banner report-wrap-module report-scroll-module">
                    <div class="head-content bili-wrapper">
                    {}
                    </div>

                </div>
                </div>
                <Switch>
                    <Route exact path='/' component={PageLogin}/>
                    <Route path='/register' component={RegisterForm}/>
                    <Route path='/login' component={PageLogin}/>
                    <Route path='/adminbooks' component={PageAdmin}/>
                </Switch>
            </div>
        )
    }
}

export default Index;