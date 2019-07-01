import React, {Component} from 'react';
import { Form, Icon, Button} from 'antd';
import {instanceOf} from 'prop-types';
import { withCookies,Cookies} from 'react-cookie'
import {Link} from 'react-router-dom'
import { Segment ,Input} from 'semantic-ui-react'

export var logintype='NotSet'
let url='http://localhost:8080/services/remember'
let options = {}
options.method = 'GET'
options.mode = 'cors'
options.credentials = 'include'
console.log("outer")
fetch(url,options).then(function(response){return response.text()})
.then(function(res){
  logintype=res;
  window.location.href='#'
})

class NormalLoginForm extends React.Component {
  static propTypes = {
    cookies: instanceOf(Cookies).isRequired
  }
  handleSubmit = (e) => {
    e.preventDefault();
    const { cookies} = this.props
    this.props.form.validateFields((err, values) => {
      if (!err) {
        console.log('Received values of form: ', values);
        let url='http://localhost:8080/services/login'
        let options={}
        options.method='POST'
        options.headers={ 'Accept': 'application/json', 'Content-Type': 'application/json', }
        options.body=JSON.stringify(values)
        console.log(options);
        fetch(url,options).then(response=>response.text())
          .then(responseJson=>{
            alert(responseJson);
            logintype=responseJson
            if(logintype=="admin" || logintype=="user"){
              this.props.history.push("/books");
            }
            window.location.href='#'
        }).catch(function(e) {
              console.log("Oops, error");
        });
        cookies.set('username',values.userName)
        cookies.set('password',values.password)
      }
    });
  }
  
  render()
    {
        const { getFieldDecorator } = this.props.form;
        return(
            <Segment style={{textAlign:'center'}}>
            <Form onSubmit={this.handleSubmit} >
                <Form.Item>
                    { getFieldDecorator("userName", {
                        rules: [{ required: true, message: "Please input your username!"}],
                        })(
                            <Input prefix={ <Icon type="user" style={{ color: "rgba(0,0,0,.25)" }} />} value=""  placeholder="Username" />
                        )}
                </Form.Item>
                <Form.Item>
                    { getFieldDecorator("password", {
                        rules: [{ required: true, message: "Please input your password!" }],
                    })(
                        <Input prefix={ <Icon type="lock" style={{ color: "rgba(0,0,0,.25)" }} />} type="password" value="" placeholder="Password" />
                    )}
                </Form.Item>
                <Form.Item>
                    <Button type="primary" onClick={ this.handleSubmit }>Log in</Button>or
                <Link to="/register">
                    <Button type="primary" >Register</Button>
                </Link>
                </Form.Item>
            </Form>
            </Segment>
        );
    }
}

const PageLogin = Form.create()(NormalLoginForm);

export default withCookies(PageLogin);