const extractDataSet = (arr) => {


    // 전체 정보 가져오기
    const total = arr.length;

    //FD_CS(음식카테고리) 만 가져오기
    const cat_arr = arr.map((item) => item.FD_CS)
        .sort((a, b) => a.localeCompare(b));
    console.log('cat_arr', cat_arr);

    // FD_CS(음식카테고리) 별로 카테고리 누적
    const cat_obj = arr.reduce((acc, item) => {
        const { FD_CS } = item;
        if (acc.hasOwnProperty(FD_CS)) {
            acc[FD_CS] += 1; // 이미 해당 FD_CS가 존재하면 카운트를 1 증가
        } else {
            acc[FD_CS] = 1; // 해당 FD_CS가 처음 나타나면 카운트를 1로 초기화
        }
        return acc;
    }, {});

    // 없는 카테고리에 대해 카운트 0으로 추가
    const categories = ['디저트/베이커리', '세계요리', '양식', '일식', '전통차/커피전문점', '중식', '특별한 술집', '퓨전/뷔페', '한식'];
    categories.forEach((category) => {
        if (!cat_obj.hasOwnProperty(category)) {
            cat_obj[category] = 0;
        }
    });


    //  Key추출(오름차순)
    const keys = Object.entries(cat_obj)
        .sort((a, b) => a[0].localeCompare(b[0]))
        .map(([key, value]) => key);
    console.log('keys', keys);
    // Value추출(오름차순)
    const values = Object.entries(cat_obj)
        .sort((a, b) => a[0].localeCompare(b[0]))
        .map(([key, value]) => value);
    console.log('values', values);


    return {
        keys: keys,
        values: values
    };


}