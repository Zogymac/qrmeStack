import './HashTags.module.scss';

const HashTag = (props) => {
    return (
        <div className={styles.HashTag}>
            <p className={styles.TextHashTag}>{props.tag}</p>
        </div>
    );
}

export default HashTag;