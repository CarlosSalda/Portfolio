import '../css/InstagramApp.css';
import FormContainer from './FormContainer.jsx';

const Start = () => {
  return (
      <div className="StartWrapper">
        <div className="box Header">
            <div><img className= "ImageLeft" src= "Images/InstagramLogo.png" alt= "InstagramLogo"/></div>
            <div><img className= "ImageCenter" src= "Images/InstagramLettersO.png" alt= "InstagramLettersO"/></div>
            <div><img className= "ImageRight" src= "Images/Menu.png" alt= "Menu"/></div>
            
        </div>
        <div className="box Lateral">
            <img className= "Logo" src= "Images/InstagramLetters.png" alt= "InstagramLetters"/>
            
        </div>
        <FormContainer />
        <div className="box Bottom"></div>
      </div>
  )
}

export default Start;