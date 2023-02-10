import React from 'react';

// CSS
import './Marker.scss';

import {
  Room
} from '@mui/icons-material';

const Marker = (props: any) => {
  return (
    <>
      <Room onClick={props.onClick} fontSize="large" sx={{ color: "#F12121" }}/>
    </>
  )
}

export default Marker;