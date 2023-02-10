
import HomeIcon from '../../components/UI/HomeIcon/HomeIcon';
import Layout from '../../components/UI/Layout/Layout';
import MenuItem from '../../models/general/MenuItem';
import { useAppSelector } from '../../store/configs/hooks';
import { useGetMenuItens } from '../../utils/MenuItemsUtils';

import './Home.scss'

const Home = () => {

  const { itensMenu } = useGetMenuItens();

  const selector = useAppSelector((state) => state);
  console.log(selector);

  
  return (
    <Layout headerTitle='Home'>
      <h1>Página Inicial</h1>
      <div className="iconesWrapper">
        {
          itensMenu.filter((item: MenuItem) => item.title != "Início").map((item: MenuItem) => {
            return (
              <HomeIcon key={`${item.idMenuItem}`} menuItem={item} />
            )
          })
        }
      </div>
    </Layout>

  );
}

export default Home;