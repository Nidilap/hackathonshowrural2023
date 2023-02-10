import React from 'react';

// CSS
import './Marker.scss';


const Marker = (props: any) => {
  return (
    <div className="marcador" onClick={props.onClick}></div>
  )
}

export default Marker;