import styles from './userVW.module.scss';
import user_info from '../../scripts/work-with-server';
import UserImage from '../../assets/images/Andrey.svg';
import UserIcon from '../../assets/icons/UserIcon.svg';
import IconVK from '../../assets/icons/VKIcon.png';
import IconTelegram from '../../assets/icons/TelegramIcon.png';
import IconViber from '../../assets/icons/ViberIcon.png';
import IconWhatsApp from '../../assets/icons/whatsappicon.png';
import IconTelephone from '../../assets/icons/TelephoneIcon.png';
import MailIcon from '../../assets/icons/MailIcon.png';

let myResult;
user_info.then(result => {
    console.log(result)
    myResult = result
}).catch (error => {
    console.error(error)
})

console.log(myResult)

const userVW = () => {
  return (
    <div className={styles.UserView}>
        <div className={styles.UserView_image_container}>
            <img src={UserImage} alt="UserImage.svg" />
        </div>
        <div className={styles.UserView_icon_container}>
            <img src={UserIcon} alt="UserIcon.svg" />
        </div>
        <div className={styles.UserView_UserName}>
            <div className={styles.UserName}>
                <h2>Андрей Волошко</h2>
            </div>
            <div className={styles.UserPosition}>
                <h3>Product-менеджер</h3>
            </div>
        </div>
        <div className={styles.UserLinks_container}>
            <div className={styles.UserLinks}>
                <div className={styles.Messengers}>
                    <a href="/">
                        <div className={styles.UserLinks_VK}>
                            <img src={IconVK} alt="IconVK.png" />
                        </div>
                    </a>
                    <a href="/">
                        <div className={styles.UserLinks_Telegram}>
                            <img src={IconTelegram} alt="IconTelegram.png" />
                        </div>
                    </a>
                    <a href="/">
                        <div className={styles.UserLinks_WhatsApp}>
                            <img src={IconWhatsApp} alt="IconWhatsApp.png" />
                        </div>
                    </a>
                    <a href="/">
                        <div className={styles.UserLinks_Viber}>
                            <img src={IconViber} alt="IconViber.png" />
                        </div>
                    </a>
                </div>
                <div className={styles.TelephoneAndMail}>
                    <a href="/">
                        <div className={styles.UserLinks_Telephone}>
                            <img src={IconTelephone} alt="IconTelephone.png" />
                        </div>
                    </a>
                    <a href="/">
                        <div className={styles.UserLinks_Mail}>
                            <img src={MailIcon} alt="IconMail.png" />
                        </div>
                    </a>
                </div>
            </div>
        </div>
        <div className={styles.Notes_container}>
            <h2 className={styles.About_Me}>О себе</h2>
            <div className={styles.remark_container}>
                <p className={styles.remark_container_text}>
                Медицина: врачи, стоматологи и другие медицинские
                специалисты могут использовать сервис цифровых визиток 
                для предоставления контактных данных, информации о своих
                </p>
            </div>
        </div>
        <div className={styles.Competitions}>
            <div className={styles.Competitions_container_text}>
                <h2 className={styles.Competitions_text}>Компетиции</h2>
            </div>
        </div>
    </div>
  )
}

export default userVW;
