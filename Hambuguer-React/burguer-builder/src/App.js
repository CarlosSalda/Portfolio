import Layout from './components/Layout/Layout';
import logo from './logo.svg';
import BurgerBuilder from './containers/BurgerBuilder/BurgerBuilder'


function App() {
  return (
    <div>
      <Layout>
        <BurgerBuilder/>
      </Layout>
    </div>
  );
}

export default App;
