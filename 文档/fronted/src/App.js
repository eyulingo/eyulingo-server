import React, { 
  Component
} from 'react';
//import logo from './logo.svg';
import './App.css';
import {BrowserRouter} from 'react-router-dom'
import Index from './components/INDEX'
import {CookiesProvider} from 'react-cookie'


class App extends Component {
  render() {
    return (
      <CookiesProvider>
      <div className="App">

        <BrowserRouter>
          {}
          <Index />
        </BrowserRouter>
        {}
        {}
      </div>
      </CookiesProvider>
    );
  }
  
}



export default App;
