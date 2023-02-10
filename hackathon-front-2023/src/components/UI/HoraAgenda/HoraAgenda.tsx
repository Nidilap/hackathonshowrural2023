import React, { ReactNode } from 'react';

// CSS
import './HoraAgenda.scss';

interface HoraAgendaProps {
    children: ReactNode;
    hora: string;

	//Todas as outras props.
	[x: string]: any;
}

const HoraAgenda = (props: HoraAgendaProps) => {
  return (
    <div className='horaAgendaWrapper'>
        <div className="horaLinhaWrapper">
            <div className='hora'>
                {props.hora}h
            </div>
            <div className='linha'>                
            </div>
        </div>
        <div className="wrapperChildren">
            {props.children}
        </div>
    </div>
  )
}

export default HoraAgenda;