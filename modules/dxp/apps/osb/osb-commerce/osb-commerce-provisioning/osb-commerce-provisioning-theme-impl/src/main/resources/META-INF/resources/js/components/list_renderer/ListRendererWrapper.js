import React from 'react';
import CardListRenderer from './CardListRenderer';

function ListRendererWrapper(props) {
	return <CardListRenderer {...props} />;
}

export default ListRendererWrapper;