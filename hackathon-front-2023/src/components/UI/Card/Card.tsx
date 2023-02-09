import React from 'react';

// CSS
import './Card.scss';


const Card = (props: any) => {
  return (
    <div className={`cardWrapper ${props.className}`}>{props.children}</div>
  )
}

export default Card;