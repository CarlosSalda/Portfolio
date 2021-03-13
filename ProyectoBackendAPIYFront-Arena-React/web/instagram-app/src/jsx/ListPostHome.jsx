import React from 'react';
import '../css/TimeLine.css';
import '../css/InstagramApp.css';
import TimeLinePost from './TimeLinePost'

const ListPostHome = ({timeLine}) => {

    return (
        <div className = "box Lateral Posts">
            {timeLine.map(post => (
                        <TimeLinePost key={post.id} post ={post}/>               
            ))}        
        </div>
    );
};

export default ListPostHome;