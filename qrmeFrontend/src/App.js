import Header from './components/Header/Header';
import UserVW from './components/userVW/userVW';
import Footer from './components/Footer/Footer';
import styles from "./global-styles/index.module.scss";

const App = () => {
  return (
    <div className={styles.App}>
      <Header />
      <UserVW />
      <Footer />
    </div>
  )
}

export default App