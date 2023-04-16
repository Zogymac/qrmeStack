import HashTag from './HashTag';

let n = 0;
const HashTags = () => {
  return (
    <Parent>
        { [...Array(n)].map((index, content) => <HashTag key={index} name={content}/> ) }
    </Parent>
  )
}

export default HashTags;