import React, {Component} from 'react';
import { Segment ,Input} from 'semantic-ui-react'
import ReactDOM from 'react-dom';

import { Form, Tooltip, Icon, Select ,Button, AutoComplete } from 'antd';
const FormItem = Form.Item;
const Option = Select.Option;
const AutoCompleteOption = AutoComplete.Option;



class PageRegister extends React.Component {
  state = {
    confirmDirty: false,
    autoCompleteResult: [],
  };
  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFieldsAndScroll((err, values) => {
      if (!err) {
        console.log('Received values of form: ', values);
        let url='http://localhost:8080/services/register'
        let options={}
        options.method='POST'
        options.headers={ 'Accept': 'application/json', 'Content-Type': 'application/json', }
        options.body=JSON.stringify(values)
        console.log(options);
        fetch(url,options).then(response=>response.text())
          .then(responseJson=>{
            alert(responseJson);
        }).catch(function(e) {
              console.log("Oops, error");
        });
      }
    });
  }
  handleConfirmBlur = (e) => {
    const value = e.target.value;
    this.setState({ confirmDirty: this.state.confirmDirty || !!value });
  }
  compareToFirstPassword = (rule, value, callback) => {
    const form = this.props.form;
    if (value && value !== form.getFieldValue('password')) {
      callback('Two passwords that you enter is inconsistent!');
    } else {
      callback();
    }
  }
  validateToNextPassword = (rule, value, callback) => {
    const form = this.props.form;
    if (value && this.state.confirmDirty) {
      form.validateFields(['confirm'], { force: true });
    }
    callback();
  }
  render() {
    const { getFieldDecorator } = this.props.form;

    return (
      <Segment style={{textAlign:'center'}}>
      <Form onSubmit={this.handleSubmit}>
        <FormItem>
          {getFieldDecorator('email', {
            rules: [{
              type: 'email', message: 'The input is not valid E-mail!',
            }, {
              required: true, message: 'Please input your E-mail!',
            }],
          })(
            <Input onChange={ this.emailChange } id='邮箱' placeholder='邮箱' />
          )}
        </FormItem><br/>
        <FormItem>
          {getFieldDecorator('password', {
            rules: [{
              required: true, message: 'Please input your password!',
            }, {
              validator: this.validateToNextPassword,
            }],
          })(
            <Input type="password" onChange={ this.passwordChange } id='密码' placeholder='密码'/>
          )}
        </FormItem><br/>
        <FormItem>
          {getFieldDecorator('confirm', {
            rules: [{
              required: true, message: 'Please confirm your password!',
            }, {
              validator: this.compareToFirstPassword,
            }],
          })(
            <Input type="password" onBlur={this.handleConfirmBlur} id='确认密码' placeholder='确认密码'/>
          )}
        </FormItem><br/>
        <FormItem>
          {getFieldDecorator('nickname', {
            rules: [{ required: true, message: 'Please input your nickname!', whitespace: true }],
          })(
            <Input onChange={ this.userChange } id='昵称' placeholder='昵称'/>
          )}
        </FormItem><br/>
        <FormItem>
          {getFieldDecorator('phone', {
            rules: [{ required: true, message: 'Please input your phone number!' }],
          })(
            <Input onChange={ this.phoneChange } id='手机号码' placeholder='手机号码' />
          )}
        </FormItem><br/>
        <FormItem>
          <Button type="primary" onClick={ this.handleSubmit } >Register</Button>
        </FormItem>
      </Form>
      </Segment>
    );
  }
}




const RegisterForm = Form.create()(PageRegister);



export default RegisterForm;
